package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.Building;
import cn.ddzu.shop.entity.Metro;
import cn.ddzu.shop.entity.Office;
import cn.ddzu.shop.entity.Zone;
import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.service.BasicService;
import cn.ddzu.shop.service.OfficeService;
import cn.ddzu.shop.util.JsonUtils;
import cn.ddzu.shop.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/edit")
public class EditController extends BaseController {

    @Autowired
    private BasicService basicService;
    @Autowired
    private OfficeService officeService;

    /**
     * 重置
     */
    @RequestMapping("/reset")
    public void reset(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-reset", helper);

        basicService.reset();
        officeService.reset();

        finish(response, SUCCESS_MESSAGE);
    }

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

        String name = helper.getString("name");
        Long zone_id = helper.getLong("zone_id");
        String address = helper.getString("address");
        List<Long> metro_list = helper.getLongList("metro_list");
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

        finish(response, SUCCESS_MESSAGE);
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

        Long id = helper.getLong("id");
        officeService.deleteBuilding(id);

        finish(response, SUCCESS_MESSAGE);
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

        Long id = helper.getLong("id");
        String name = helper.getString("name");
        Long zone_id = helper.getLong("zone_id");
        String address = helper.getString("address");
        List<Long> metro_list = helper.getLongList("metro_list");
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

        finish(response, SUCCESS_MESSAGE);
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
    public void getBuildingList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("search-buildings", helper);

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
            JsonObject buildingObject = new Gson().toJsonTree(building).getAsJsonObject();
            buildingObject.add("img_list", JsonUtils.strToStringArray(building.getImg_list()));
            buildingObject.add("metro_name_list", JsonUtils.strToStringArray(building.getMetro_name_list()));
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

        finish(response, SUCCESS_MESSAGE, json);
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

        finish(response, SUCCESS_MESSAGE);
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

        Long id = helper.getLong("id");
        officeService.deleteOffice(id);

        finish(response, SUCCESS_MESSAGE);
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

        finish(response, SUCCESS_MESSAGE);
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
    public void getOfficeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("search-offices", helper);

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

        List<Office> officeList = officeService.getOfficeList(searchBean, page, PAGE_SIZE);
        JsonArray officeArray = new JsonArray();
        for (Office office : officeList) {
            JsonObject officeObject = new Gson().toJsonTree(office).getAsJsonObject();
            officeObject.add("img_list", JsonUtils.strToStringArray(office.getImg_list()));
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

        finish(response, SUCCESS_MESSAGE, json);
    }
}
