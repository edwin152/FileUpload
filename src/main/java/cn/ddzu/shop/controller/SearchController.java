package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.Building;
import cn.ddzu.shop.entity.Metro;
import cn.ddzu.shop.entity.Office;
import cn.ddzu.shop.entity.Zone;
import cn.ddzu.shop.enums.ResultCode;
import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.service.FilterService;
import cn.ddzu.shop.service.OfficeService;
import cn.ddzu.shop.util.JsonUtils;
import cn.ddzu.shop.util.Log;
import cn.ddzu.shop.util.StringUtils;
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
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController extends BaseController {

    @Autowired
    private OfficeService officeService;
    @Autowired
    private FilterService filterService;

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

        Long id = helper.getLong("id");

        Building building = officeService.getBuilding(id);
        JsonObject buildingObject = new Gson().toJsonTree(building).getAsJsonObject();
        buildingObject.add("metro_name_list", JsonUtils.strToStringArray(building.getMetro_name_list()));
        buildingObject.add("img_list", JsonUtils.strToStringArray(building.getImg_list()));

        OfficeService.SearchBean officeSearchBean = new OfficeService.SearchBean();
        officeSearchBean.setBuilding_id(id);
        List<Office> officeList = officeService.getOfficeList(officeSearchBean, 0, -1);
        int lastIndex = officeList.size() == 0 ? 0 : officeList.size() - 1;

        // 价格区间
        officeList.sort(new Comparator<Office>() {
            @Override
            public int compare(Office o1, Office o2) {
                return Float.compare(o1.getPrice(), o2.getPrice());
            }
        });
        Float firstPrice = officeList.get(0).getPrice();
        Float lastPrice = officeList.get(lastIndex).getPrice();
        StringBuilder priceRange = new StringBuilder().append(firstPrice);
        if (Float.compare(firstPrice, lastPrice) != 0) {
            priceRange.append(" - ").append(lastPrice);
        }
        buildingObject.addProperty("price_range", priceRange.toString());

        // 面积区间
        officeList.sort(new Comparator<Office>() {
            @Override
            public int compare(Office o1, Office o2) {
                return Float.compare(o1.getArea(), o2.getArea());
            }
        });
        Float firstArea = officeList.get(0).getArea();
        Float lastArea = officeList.get(lastIndex).getArea();
        StringBuilder areaRange = new StringBuilder().append(firstArea);
        if (Float.compare(firstArea, lastArea) != 0) {
            areaRange.append(" - ").append(lastArea);
        }
        buildingObject.addProperty("area_range", areaRange.toString());

        buildingObject.addProperty("office_num", officeList.size());

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

        String keyword = helper.getString("keyword");
        Long district_id = helper.getLong("district_id", 1L);
        Long zone_id = helper.getLong("zone_id", 1L);
        Long metro_id = helper.getLong("metro_id", 1L);
        Long type_id = helper.getLong("type_id", 1L);
        Long area_range_id = helper.getLong("area_range_id", 1L);
        Long price_range_id = helper.getLong("price_range_id", 1L);
        Long decoration_id = helper.getLong("decoration_id", 1L);
        Integer page = helper.getInt("page", 0);

        Metro metro = filterService.getMetro(metro_id);
        Zone zone = filterService.getZone(zone_id);
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
        List<Building> buildingList = officeService.getBuildingListWithOffice(searchBean, page, PAGE_SIZE);
        JsonArray buildingArray = new JsonArray();
        for (Building building : buildingList) {
            JsonObject buildingObject = new Gson().toJsonTree(building).getAsJsonObject();
            buildingObject.add("img_list", JsonUtils.strToStringArray(building.getImg_list()));

            OfficeService.SearchBean officeSearchBean = (OfficeService.SearchBean) searchBean.clone();
            officeSearchBean.setKeyword(null);
            officeSearchBean.setBuilding_id(building.getId());
            List<Office> officeList = officeService.getOfficeList(officeSearchBean, 0, -1);
            if (officeList.isEmpty()) {
                continue;
            }

            List<Float> areaList = new ArrayList<>();
            float priceSum = 0;
            for (Office office : officeList) {
                float area = office.getArea();
                if (areaList.indexOf(area) == -1) {
                    areaList.add(area);
                }
                priceSum += office.getPrice();
            }
            Collections.sort(areaList);
            StringBuilder areaRange = new StringBuilder();
            areaRange.append(areaList.get(0));
            if (areaList.size() > 1) {
                areaRange.append(" - ")
                        .append(areaList.get(areaList.size() - 1))
                        .append("m²");
            }
            buildingObject.addProperty("office_num", officeList.size());
            buildingObject.addProperty("price_average", priceSum / officeList.size());
            buildingObject.addProperty("area_range", areaRange.toString());
            buildingObject.add("area_list", new Gson().toJsonTree(areaList));
            buildingArray.add(buildingObject);
        }

        int size = officeService.getBuildingSizeWithOffice(searchBean);
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
     * 查询办公室
     * <p>
     * id 办公室id
     */
    @RequestMapping("/office")
    public void office(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("search-office", helper);

        Long id = helper.getLong("id");

        JsonObject json = new JsonObject();

        Office office = officeService.getOffice(id);
        if (office == null) {
            response.getWriter().write("{\"resultCode\":\"2\",\"resultMsg\":\"office_id错误\"}");
            response.getWriter().close();
            return;
        }
        Building building = officeService.getBuilding(office.getBuilding_id());
        if (building == null) {
            response.getWriter().write("{\"resultCode\":\"2\",\"resultMsg\":\"office_id错误\"}");
            response.getWriter().close();
            return;
        }
        JsonObject officeObject = new Gson().toJsonTree(office).getAsJsonObject();
        JsonObject buildingObject = new Gson().toJsonTree(building).getAsJsonObject();
        officeObject.add("img_list", JsonUtils.strToStringArray(office.getImg_list()));
        buildingObject.add("img_list", JsonUtils.strToStringArray(building.getImg_list()));
        buildingObject.add("metro_name_list", JsonUtils.strToStringArray(building.getMetro_name_list()));

        // 房源信息
        String sourceInfo = building.getDistrict_name() + building.getZone_name()
                + "【" + building.getName() + "】" + office.getType_name()
                + "-" + office.getDecoration_name()
                + "-" + (office.getCan_register() ? "可注册" : "不可注册")
                + "-" + office.getArea() + "m²";
        officeObject.addProperty("source_info", sourceInfo);

        // 可容纳工位数
        int minAccommodation = (int) (office.getArea() / 8);
        int maxAccommodation = (int) (office.getArea() / 4);
        officeObject.addProperty("workplace_accommodation"
                , minAccommodation + " ~ " + maxAccommodation);

        // 总价
        float totalPrice = 0F;
        if (office.getPrice() != null && office.getArea() != null) {
            totalPrice = office.getPrice() * office.getArea() * 30;
        }
        officeObject.addProperty("total_price", StringUtils.priceFormat(totalPrice));

        json.add("building", buildingObject);
        json.add("office", officeObject);

        finish(response, ResultCode.SUCCESS, json);
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
}
