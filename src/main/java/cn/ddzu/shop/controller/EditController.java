package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.enums.ResultCode;
import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.manager.LoginManager;
import cn.ddzu.shop.service.*;
import cn.ddzu.shop.util.JsonUtils;
import cn.ddzu.shop.util.Log;
import cn.ddzu.shop.util.SessionUtils;
import cn.ddzu.shop.util.StringUtils;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/edit")
public class EditController extends BaseController {

    @Autowired
    private BasicService basicService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userSerivce;
    @Autowired
    private SyncService syncService;

    /**
     * 新增楼
     * <p>
     * name 名字
     * zone_id 区域id
     * address 地址
     * metro_list 地铁集合[2,4,6]
     * introduce 楼盘介绍
     * notes 备注
     * decoration_id 装修类型id
     * img_list 图片
     */
    @RequestMapping("/addBuilding")
    public void addBuilding(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-addBuilding", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        String name = helper.getString("name");
        Long zone_id = helper.getLong("zone_id");
        String address = helper.getString("address");
        List<Long> metro_list = helper.getList("metro_list", LONG_LIST_TYPE);
        String introduce = helper.getString("introduce");
        String notes = helper.getString("notes", "{}");
        String img_list = helper.getString("img_list", "[]");

        Collections.sort(metro_list);
        List<String> metro_name_list = new ArrayList<>();
        for (Long metro_id : metro_list) {
            Metro metro = basicService.getMetro(metro_id);
            if (metro != null) {
                metro_name_list.add(metro.getName());
            }
        }

        Building building = new Building();
        building.setName(name);
        building.setZone_id(zone_id);
        building.setAddress(address);
        building.setMetro_name_list(new Gson().toJson(metro_name_list));
        building.setIntroduce(introduce);
        building.setNotes(notes);
        building.setImg_list(img_list);

        officeService.addBuilding(building);

        JsonObject buildingObject = new Gson().toJsonTree(building).getAsJsonObject();
        buildingObject.add("img_list", JsonUtils.strToStringArray(img_list));
        buildingObject.add("metro_name_list", new Gson().toJsonTree(metro_name_list));

        finish(response, ResultCode.SUCCESS, buildingObject);
    }

    /**
     * 删除楼
     * <p>
     * id 楼id
     */
    @RequestMapping("/deleteBuilding")
    public void deleteBuilding(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-deleteBuilding", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        Long id = helper.getLong("id");
        officeService.deleteBuilding(id);

        finish(response, ResultCode.SUCCESS);
    }

    /**
     * 修改楼
     * <p>
     * id 楼id
     * name 名字
     * zone_id 区域id
     * address 地址
     * metro_list 地铁集合[2,4,6]
     * introduce 楼盘介绍
     * notes 备注
     * decoration_id 装修类型id
     * img_list 图片
     */
    @RequestMapping("/editBuilding")
    public void editBuilding(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-editBuilding", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        Long id = helper.getLong("id");
        String name = helper.getString("name");
        Long zone_id = helper.getLong("zone_id");
        String address = helper.getString("address");
        List<Long> metro_list = helper.getList("metro_list", LONG_LIST_TYPE);
        String introduce = helper.getString("introduce");
        String notes = helper.getString("notes", "{}");
        String img_list = helper.getString("img_list", "[]");

        Building building = officeService.getBuilding(id);
        boolean insertModify = false;
        if (building == null) {
            insertModify = true;
            building = new Building();
        }

        Collections.sort(metro_list);
        List<String> metro_name_list = new ArrayList<>();
        for (Long metro_id : metro_list) {
            Metro metro = basicService.getMetro(metro_id);
            if (metro != null) {
                metro_name_list.add(metro.getName());
            }
        }

        building.setName(name);
        building.setZone_id(zone_id);
        building.setAddress(address);
        building.setMetro_name_list(new Gson().toJson(metro_name_list));
        building.setIntroduce(introduce);
        building.setNotes(notes);
        building.setImg_list(img_list);

        if (insertModify) {
            officeService.addBuilding(building);
        } else {
            officeService.updateBuilding(building);
        }

        finish(response, ResultCode.SUCCESS);
    }

    /**
     * 查询楼
     * <p>
     * id 楼id
     */
    @RequestMapping("/building")
    public void building(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("search-building", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        Long id = helper.getLong("id");

        Building building = officeService.getBuilding(id);
        JsonObject buildingObject = new Gson().toJsonTree(building).getAsJsonObject();
        buildingObject.add("img_list", JsonUtils.strToStringArray(building.getImg_list()));
        buildingObject.add("metro_name_list", JsonUtils.strToStringArray(building.getMetro_name_list()));

        finish(response, ResultCode.SUCCESS, buildingObject);
    }

    /**
     * 搜索楼
     * <p>
     * keyword 关键词
     * zone_id 区域id
     * metro_id 地铁id
     * type_id 办公室类型id
     * area_range_id 面积区间id
     * price_range_id 价格区间id
     * decoration_id 装修类型id
     * page 页码
     */
    @RequestMapping("/buildings")
    public void buildings(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("search-buildings", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        String keyword = helper.getString("keyword");
        Long district_id = helper.getLong("district_id", 1L);
        Long zone_id = helper.getLong("zone_id", 1L);
        Long metro_id = helper.getLong("metro_id", 1L);
        Long type_id = helper.getLong("type_id", 1L);
        Long area_range_id = helper.getLong("area_range_id", 1L);
        Long price_range_id = helper.getLong("price_range_id", 1L);
        Long decoration_id = helper.getLong("decoration_id", 1L);
        Integer page = helper.getInt("page", 0);

        Metro metro = basicService.getMetro(metro_id);
        Zone zone = basicService.getZone(zone_id);
        if (zone == null || zone.getDistrict_id() != district_id) {
            zone_id = district_id;
        }

        OfficeService.SearchBean searchBean = new OfficeService.SearchBean();
        searchBean.setKeyword(keyword);
        searchBean.setDistrict_id(district_id);
        searchBean.setZone_id(zone_id);
        searchBean.setMetro_name(metro);
        searchBean.setType_id(type_id);
        searchBean.setArea_range_id(area_range_id);
        searchBean.setPrice_range_id(price_range_id);
        searchBean.setDecoration_id(decoration_id);

        List<Building> buildingList = officeService.getBuildingList(searchBean, page, PAGE_SIZE);
        JsonArray buildingArray = new JsonArray();
        for (Building building : buildingList) {
            OfficeService.SearchBean officeSearchBean = (OfficeService.SearchBean) searchBean.clone();
            officeSearchBean.setKeyword(null);
            officeSearchBean.setBuilding_id(building.getId());
            int officeCount = officeService.getOfficeSize(officeSearchBean);

            JsonObject buildingObject = new Gson().toJsonTree(building).getAsJsonObject();
            buildingObject.add("img_list", JsonUtils.strToStringArray(building.getImg_list()));
            buildingObject.add("metro_name_list", JsonUtils.strToStringArray(building.getMetro_name_list()));
            buildingObject.addProperty("office_num", officeCount);

            buildingArray.add(buildingObject);
        }

        int size = officeService.getBuildingSize(searchBean);
        int pageNum = size == 0 ? 0 : (size - 1) / PAGE_SIZE + 1;

        JsonObject json = new JsonObject();
        json.add("buildingList", buildingArray);
        json.addProperty("checkedDistrictId", district_id);
        json.addProperty("checkedZoneId", zone_id);
        json.addProperty("checkedMetroId", metro_id);
        json.addProperty("checkedTypeId", type_id);
        json.addProperty("checkedAreaRangeId", area_range_id);
        json.addProperty("checkedPriceRangeId", price_range_id);
        json.addProperty("checkedDecorationId", decoration_id);
        json.addProperty("pageNum", pageNum);
        json.addProperty("pageIndex", page);

        finish(response, ResultCode.SUCCESS, json);
    }

    /**
     * 新增办公室
     * <p>
     * name 名字
     * building_id 商圈id
     * address 地址
     * type_id 办公室类型id
     * area 面积
     * price 单平米价格
     * decoration_id 装修类型id
     * utilization_rate 使用率 0.7
     * can_register 是否可注册 true false
     * rent_free_period 免租期
     * notes 备注
     * img_list 图片
     */
    @RequestMapping("/addOffice")
    public void addOffice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-addOffice", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        String name = helper.getString("name");
        Long building_id = helper.getLong("building_id");
        String address = helper.getString("address");
        Long type_id = helper.getLong("type_id");
        Float area = helper.getFloat("area");
        Float price = helper.getFloat("price");
        Long decoration_id = helper.getLong("decoration_id");
        Float utilization_rate = helper.getFloat("utilization_rate");
        Boolean can_register = helper.getBoolean("can_register", false);
        String rent_free_period = helper.getString("rent_free_period");
        String notes = helper.getString("notes", "{}");
        String img_list = helper.getString("img_list");

        Long area_range_id = basicService.getAreaRangeId(area);
        Long price_range_id = basicService.getPriceRangeId(price);

        Office office = new Office();
        office.setName(name);
        office.setBuilding_id(building_id);
        office.setAddress(address);
        office.setType_id(type_id);
        office.setArea(area);
        office.setArea_range_id(area_range_id);
        office.setPrice(price);
        office.setPrice_range_id(price_range_id);
        office.setDecoration_id(decoration_id);
        office.setUtilization_rate(utilization_rate);
        office.setCan_register(can_register);
        office.setRent_free_period(rent_free_period);
        office.setNotes(notes);
        office.setImg_list(img_list);

        officeService.addOffice(office);

        JsonObject officeObject = new Gson().toJsonTree(office).getAsJsonObject();
        officeObject.add("img_list", JsonUtils.strToStringArray(img_list));

        finish(response, ResultCode.SUCCESS, officeObject);
    }

    /**
     * 删除办公室
     * <p>
     * id 办公室id
     */
    @RequestMapping("/deleteOffice")
    public void deleteOffice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-deleteOffice", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        Long id = helper.getLong("id");
        officeService.deleteOffice(id);

        finish(response, ResultCode.SUCCESS);
    }

    /**
     * 修改办公室
     * <p>
     * id 办公室id
     * name 名字
     * building_id 商圈id
     * address 地址
     * type_id 办公室类型id
     * area 面积
     * price 单平米价格
     * decoration_id 装修类型id
     * utilization_rate 使用率 0.7
     * can_register 是否可注册 true false
     * rent_free_period 免租期
     * notes 备注
     * img_list 图片
     */
    @RequestMapping("/editOffice")
    public void editOffice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-editOffice", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        Long id = helper.getLong("id");
        String name = helper.getString("name");
        Long building_id = helper.getLong("building_id");
        String address = helper.getString("address");
        Long type_id = helper.getLong("type_id");
        Float area = helper.getFloat("area");
        Float price = helper.getFloat("price");
        Long decoration_id = helper.getLong("decoration_id");
        Float utilization_rate = helper.getFloat("utilization_rate");
        Boolean can_register = helper.getBoolean("can_register", false);
        String rent_free_period = helper.getString("rent_free_period");
        String notes = helper.getString("notes", "{}");
        String img_list = helper.getString("img_list");

        Office office = officeService.getOffice(id);
        boolean insertModify = false;
        if (office == null) {
            insertModify = true;
            office = new Office();
        }

        Long area_range_id = basicService.getAreaRangeId(area);
        Long price_range_id = basicService.getPriceRangeId(price);

        office.setName(name);
        office.setBuilding_id(building_id);
        office.setAddress(address);
        office.setType_id(type_id);
        office.setArea(area);
        office.setArea_range_id(area_range_id);
        office.setPrice(price);
        office.setPrice_range_id(price_range_id);
        office.setDecoration_id(decoration_id);
        office.setUtilization_rate(utilization_rate);
        office.setCan_register(can_register);
        office.setRent_free_period(rent_free_period);
        office.setNotes(notes);
        office.setImg_list(img_list);

        if (insertModify) {
            officeService.addOffice(office);
        } else {
            officeService.updateOffice(office);
        }

        finish(response, ResultCode.SUCCESS);
    }

    /**
     * 查询楼
     * <p>
     * id 办公室id
     */
    @RequestMapping("/office")
    public void office(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("search-building", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        Long id = helper.getLong("id");

        Office office = officeService.getOffice(id);
        JsonObject officeObject = new Gson().toJsonTree(office).getAsJsonObject();
        officeObject.add("img_list", JsonUtils.strToStringArray(office.getImg_list()));

        finish(response, ResultCode.SUCCESS, officeObject);
    }

    /**
     * 搜索办公室
     * <p>
     * keyword 关键词
     * building_id 商圈id
     * type_id 办公室类型id
     * area_range_id 面积区间id
     * price_range_id 价格区间id
     * decoration_id 装修类型id
     * page 页码
     */
    @RequestMapping("/offices")
    public void offices(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-offices", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        Long building_id = helper.getLong("building_id");
        Long type_id = helper.getLong("type_id", 1L);
        Long area_range_id = helper.getLong("area_range_id", 1L);
        Long price_range_id = helper.getLong("price_range_id", 1L);
        Long decoration_id = helper.getLong("decoration_id", 1L);
        Integer page = helper.getInt("page", 0);

        OfficeService.SearchBean searchBean = new OfficeService.SearchBean();
        searchBean.setBuilding_id(building_id);
        searchBean.setType_id(type_id);
        searchBean.setArea_range_id(area_range_id);
        searchBean.setPrice_range_id(price_range_id);
        searchBean.setDecoration_id(decoration_id);

        Building building = officeService.getBuilding(building_id);

        List<Office> officeList = officeService.getOfficeList(searchBean, page, PAGE_SIZE);
        JsonArray officeArray = new JsonArray();
        for (Office office : officeList) {
            JsonObject officeObject = new Gson().toJsonTree(office).getAsJsonObject();
            officeObject.add("img_list", JsonUtils.strToStringArray(office.getImg_list()));

            // 房源信息
            String sourceInfo = building.getDistrict_name() + building.getZone_name()
                    + "【" + building.getName() + "】" + office.getType_name()
                    + "-" + office.getDecoration_name()
                    + "-" + (office.getCan_register() ? "可注册" : "不可注册")
                    + "-" + office.getArea() + "m²";
            officeObject.addProperty("source_info", sourceInfo);

            // 总价
            float totalPrice = 0F;
            if (office.getPrice() != null && office.getArea() != null) {
                totalPrice = office.getPrice() * office.getArea() * 30;
            }
            officeObject.addProperty("total_price", StringUtils.priceFormat(totalPrice));

            officeArray.add(officeObject);
        }

        int size = officeService.getOfficeSize(searchBean);
        int pageNum = size == 0 ? 0 : (size - 1) / PAGE_SIZE + 1;

        JsonObject json = new JsonObject();
        json.add("officeList", officeArray);
        json.addProperty("checkedTypeId", type_id);
        json.addProperty("checkedAreaRangeId", area_range_id);
        json.addProperty("checkedPriceRangeId", price_range_id);
        json.addProperty("checkedDecorationId", decoration_id);
        json.addProperty("pageNum", pageNum);
        json.addProperty("pageIndex", page);

        finish(response, ResultCode.SUCCESS, json);
    }

    /**
     * 新增咨询
     * <p>
     * title 标题
     * sub_title 副标题
     * content 内容
     * news_tag_id 新闻标签id
     * hot 是否热门
     */
    @RequestMapping("/addNews")
    public void addNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-addNews", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        String title = helper.getString("title");
        String sub_title = helper.getString("sub_title");
        String content = helper.getString("content");
        Long news_tag_id = helper.getLong("news_tag_id");
        Boolean hot = helper.getBoolean("hot", false);

        News news = new News();
        news.setTitle(title);
        news.setSub_title(sub_title);
        news.setContent(content);
        news.setNews_tag_id(news_tag_id);
        news.setHot(hot);

        newsService.addNews(news);

        JsonObject newsObject = new Gson().toJsonTree(news).getAsJsonObject();

        finish(response, ResultCode.SUCCESS, newsObject);
    }

    /**
     * 删除咨询
     * <p>
     * id 咨询id
     */
    @RequestMapping("/deleteNews")
    public void deleteNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-deleteNews", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        Long id = helper.getLong("id");
        newsService.deleteNews(id);

        finish(response, ResultCode.SUCCESS);
    }

    /**
     * 修改咨询
     * <p>
     * id 办公室id
     * title 标题
     * sub_title 副标题
     * content 内容
     */
    @RequestMapping("/editNews")
    public void editNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-editNews", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        Long id = helper.getLong("id");
        String title = helper.getString("title");
        String sub_title = helper.getString("sub_title");
        String content = helper.getString("content");

        News news = newsService.getNews(id);
        boolean insertModify = false;
        if (news == null) {
            insertModify = true;
            news = new News();
        }

        news.setTitle(title);
        news.setSub_title(sub_title);
        news.setContent(content);

        if (insertModify) {
            newsService.addNews(news);
        } else {
            newsService.updateNews(news);
        }

        finish(response, ResultCode.SUCCESS);
    }

    /**
     * 查询咨询
     * <p>
     * id 咨询id
     */
    @RequestMapping("/news")
    public void news(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("search-news", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        Long id = helper.getLong("id");

        News news = newsService.getNews(id);
        JsonObject newsObject = new Gson().toJsonTree(news).getAsJsonObject();

        finish(response, ResultCode.SUCCESS, newsObject);
    }

    /**
     * 搜索咨询
     * <p>
     * news_tag_id 新闻标签
     * page 页码
     */
    @RequestMapping("/newsList")
    public void newsList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-newsList", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        Long news_tag_id = helper.getLong("news_tag_id", 1L);
        Integer page = helper.getInt("page", 0);

        List<News> newsList = newsService.getNewsList(news_tag_id, page, PAGE_SIZE);
        for (News news : newsList) {
            news.setContent(null);
        }

        int size = newsService.getNewsSize(news_tag_id);
        int pageNum = size == 0 ? 0 : (size - 1) / PAGE_SIZE + 1;

        JsonObject json = new JsonObject();
        JsonArray newsArray = new Gson().toJsonTree(newsList).getAsJsonArray();
        json.add("newsList", newsArray);
        json.addProperty("checkedNewsTagId", news_tag_id);
        json.addProperty("pageNum", pageNum);
        json.addProperty("pageIndex", page);

        finish(response, ResultCode.SUCCESS, json);
    }

    /**
     * 查询区域
     */
    @RequestMapping("/zones")
    public void zones(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-zones", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        List<District> districtList = basicService.getDistrictList();
        JsonArray districtArray = new JsonArray();
        for (District district : districtList) {
            JsonObject districtObject = new Gson().toJsonTree(district).getAsJsonObject();
            long districtId = district.getId();
            List<Zone> zoneList = basicService.getZoneList(districtId);
            JsonArray zoneArray = new JsonArray();
            for (Zone zone : zoneList) {
                JsonObject zoneObject = new Gson().toJsonTree(zone).getAsJsonObject();
                zoneObject.add("img_list", JsonUtils.strToStringArray(zone.getImg_list()));
                zoneArray.add(zoneObject);
            }
            districtObject.add("zoneList", zoneArray);
            districtArray.add(districtObject);
        }

        finish(response, ResultCode.SUCCESS, districtArray);
    }

    /**
     * 修改区域
     * <p>
     * zone_id 商圈id
     * center 是否为中心
     * img_list 图片
     */
    @RequestMapping("/editZone")
    public void editZone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-editZone", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (!isLogin) {
            finish(response, ResultCode.ERROR_NO_LOGIN);
            return;
        }

        Long zone_id = helper.getLong("zone_id");
        Boolean center = helper.getBoolean("center", false);
        List<String> img_list = helper.getList("img_list");

        Zone zone = basicService.getZone(zone_id);
        zone.setCenter(center);
        zone.setImg_list(new Gson().toJson(img_list));
        basicService.updateZone(zone);

        finish(response, ResultCode.SUCCESS);
    }

    /**
     * 获取所有同步数据
     * <p>
     * username 用户名
     * password 密码
     */
    @RequestMapping("/syncAll")
    public void syncAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-syncAll", helper);

        String username = helper.getString("username");
        String password = helper.getString("password");

        String tokenId = LoginManager.getInstance().login(userSerivce, username, password);
        if (tokenId == null) {
            finish(response, ResultCode.ERROR_WRONG_PASSWORD);
            return;
        }

        Map<String, List> dataMap = syncService.getAll();
        JsonObject data = new Gson().toJsonTree(dataMap).getAsJsonObject();

        finish(response, ResultCode.SUCCESS, data);
    }

    /**
     * 同步所有数据
     */
    @SuppressWarnings("unused")
//    @RequestMapping("/sync")
    public void sync(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-sync", helper);

        StringBuilder s = new StringBuilder();
        URL syncAll = new URL("http://47.96.165.78:8080/ddzu/edit/syncAll?username=edwin&password=edwin");
        URLConnection syncAllConn = syncAll.openConnection();
        InputStream is = syncAllConn.getInputStream();
        Reader r = new InputStreamReader(is, StandardCharsets.UTF_8);
        int c;
        while ((c = r.read()) > 0) {
            s.append((char) c);
        }

        JsonObject json = new JsonParser().parse(s.toString()).getAsJsonObject().get("data").getAsJsonObject();
        List<AreaRange> areaRangeList = get(json, "area_range", new TypeToken<List<AreaRange>>() {
        });
        List<Building> buildingList = get(json, "building", new TypeToken<List<Building>>() {
        });
        List<Decoration> decorationList = get(json, "decoration", new TypeToken<List<Decoration>>() {
        });
        List<District> districtList = get(json, "district", new TypeToken<List<District>>() {
        });
        List<Metro> metroList = get(json, "metro", new TypeToken<List<Metro>>() {
        });
        List<News> newsList = get(json, "news", new TypeToken<List<News>>() {
        });
        List<NewsTag> newsTagList = get(json, "news_tag", new TypeToken<List<NewsTag>>() {
        });
        List<Office> officeList = get(json, "office", new TypeToken<List<Office>>() {
        });
        List<PriceRange> priceRangeList = get(json, "price_range", new TypeToken<List<PriceRange>>() {
        });
        List<Type> typeList = get(json, "type", new TypeToken<List<Type>>() {
        });
        List<User> userList = get(json, "user", new TypeToken<List<User>>() {
        });
        List<Zone> zoneList = get(json, "zone", new TypeToken<List<Zone>>() {
        });

        syncService.setAll(areaRangeList
                , buildingList
                , decorationList
                , districtList
                , metroList
                , newsList
                , newsTagList
                , officeList
                , priceRangeList
                , typeList
                , userList
                , zoneList);

        finish(response, ResultCode.SUCCESS);
    }

    private <T> List<T> get(JsonObject json, String key, TypeToken<List<T>> typeToken) {
        return new Gson().fromJson(json.get(key), typeToken.getType());
    }
}
