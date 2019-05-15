package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.Building;
import cn.ddzu.shop.entity.Metro;
import cn.ddzu.shop.entity.Office;
import cn.ddzu.shop.entity.Zone;
import cn.ddzu.shop.service.BasicService;
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
public class SearchController {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private OfficeService officeService;
    @Autowired
    private BasicService basicService;

    /**
     * 搜索楼
     * keyword 关键词
     * zone_id 区域id
     * metro_id 地铁id
     * type_id 办公室类型id
     * area_range_id 面积区间id
     * price_range_id 价格区间id
     * decoration_id 装修类型id
     */
    @RequestMapping("/buildings")
    public void getBuildingList(HttpServletRequest request, HttpServletResponse response) throws IOException, CloneNotSupportedException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        Log.d("search-buildings", new Gson().toJson(request.getParameterMap()));

        String keyword = null;
        if (request.getParameter("keyword") != null) {
            keyword = request.getParameter("keyword");
        }

        Long district_id = 1L;
        if (request.getParameter("district_id") != null) {
            district_id = Long.parseLong(request.getParameter("district_id"));
        }

        Long zone_id = 1L;
        if (request.getParameter("zone_id") != null) {
            zone_id = Long.parseLong(request.getParameter("zone_id"));
        }

        Long metro_id = 1L;
        if (request.getParameter("metro_id") != null) {
            metro_id = Long.parseLong(request.getParameter("metro_id"));
        }

        Long type_id = 1L;
        if (request.getParameter("type_id") != null) {
            type_id = Long.parseLong(request.getParameter("type_id"));
        }

        Long area_range_id = 1L;
        if (request.getParameter("area_range_id") != null) {
            area_range_id = Long.parseLong(request.getParameter("area_range_id"));
        }

        Long price_range_id = 1L;
        if (request.getParameter("price_range_id") != null) {
            price_range_id = Long.parseLong(request.getParameter("price_range_id"));
        }

        Long decoration_id = 1L;
        if (request.getParameter("decoration_id") != null) {
            decoration_id = Long.parseLong(request.getParameter("decoration_id"));
        }

        int page = 0;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

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
        response.getWriter().write(json.toString());
        response.getWriter().close();
    }

    /**
     * 搜索办公室
     * keyword 关键词
     * building_id 商圈id
     * type_id 办公室类型id
     * area_range_id 面积区间id
     * price_range_id 价格区间id
     * decoration_id 装修类型id
     */
    @RequestMapping("/offices")
    public void getOfficeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        Log.d("search-offices", new Gson().toJson(request.getParameterMap()));

        Long building_id = null;
        if (request.getParameter("building_id") != null) {
            building_id = Long.parseLong(request.getParameter("building_id"));
        }

        Long type_id = 1L;
        if (request.getParameter("type_id") != null) {
            type_id = Long.parseLong(request.getParameter("type_id"));
        }

        Long area_range_id = 1L;
        if (request.getParameter("area_range_id") != null) {
            area_range_id = Long.parseLong(request.getParameter("area_range_id"));
        }

        Long price_range_id = 1L;
        if (request.getParameter("price_range_id") != null) {
            price_range_id = Long.parseLong(request.getParameter("price_range_id"));
        }

        Long decoration_id = 1L;
        if (request.getParameter("decoration_id") != null) {
            decoration_id = Long.parseLong(request.getParameter("decoration_id"));
        }

        int page = 0;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

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
        response.getWriter().write(json.toString());
        response.getWriter().close();
    }

    /**
     * 查询楼
     * id 办公室id
     */
    @RequestMapping("/building")
    public void getBuilding(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        Log.d("search-building", new Gson().toJson(request.getParameterMap()));

        Long id = null;
        if (request.getParameter("id") != null) {
            id = Long.parseLong(request.getParameter("id"));
        }

        Building building = officeService.getBuilding(id);
        JsonObject buildingObject = new Gson().toJsonTree(building).getAsJsonObject();
        buildingObject.add("metro_name_list", JsonUtils.strToStringArray(building.getMetro_name_list()));
        buildingObject.add("img_list", JsonUtils.strToStringArray(building.getImg_list()));

        OfficeService.SearchBean officeSearchBean = new OfficeService.SearchBean();
        officeSearchBean.setBuilding_id(id);
        List<Office> officeList = officeService.getOfficeList(officeSearchBean, 0, -1);
        int lastIndex = officeList.size() == 0 ? 0 : officeList.size() - 1;

        // 价格区间
        Collections.sort(officeList, new Comparator<Office>() {
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
        Collections.sort(officeList, new Comparator<Office>() {
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

        response.getWriter().write(buildingObject.toString());
        response.getWriter().close();
    }

    /**
     * 查询办公室
     * id 办公室id
     */
    @RequestMapping("/office")
    public void getOffice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        Log.d("search-office", new Gson().toJson(request.getParameterMap()));

        Long id = null;
        if (request.getParameter("id") != null) {
            id = Long.parseLong(request.getParameter("id"));
        }

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

        response.getWriter().write(json.toString());
        response.getWriter().close();
    }
}
