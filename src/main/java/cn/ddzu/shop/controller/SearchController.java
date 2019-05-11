package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.Building;
import cn.ddzu.shop.entity.Metro;
import cn.ddzu.shop.entity.Office;
import cn.ddzu.shop.entity.Zone;
import cn.ddzu.shop.service.BasicService;
import cn.ddzu.shop.service.OfficeService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
     * 查询楼
     * id 楼id
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

        Long id = null;
        if (request.getParameter("id") != null) {
            id = Long.parseLong(request.getParameter("id"));
        }

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
        searchBean.setId(id);
        searchBean.setKeyword(keyword);
        searchBean.setZone_id(zone_id);
        searchBean.setMetro_name(metro);
        searchBean.setType_id(type_id);
        searchBean.setArea_range_id(area_range_id);
        searchBean.setPrice_range_id(price_range_id);
        searchBean.setDecoration_id(decoration_id);

        JsonObject json = new JsonObject();
        List<Building> buildingList = officeService.getBuildingList(searchBean, page, PAGE_SIZE);
        JsonArray buildingArray = new JsonArray();
        for (Building building : buildingList) {
            String img_list = building.getImg_list();
            List<String> imgList = new Gson().fromJson(img_list, new TypeToken<String>() {
            }.getType());
            JsonObject buildingObject = new Gson().toJsonTree(building).getAsJsonObject();
            buildingObject.add("img_list", new Gson().toJsonTree(imgList));

            OfficeService.SearchBean officeSearchBean = (OfficeService.SearchBean) searchBean.clone();
            officeSearchBean.setBuilding_id(building.getId());
            List<Office> officeList = officeService.getOfficeList(officeSearchBean, 0, -1);
            if (officeList.isEmpty()) {
                continue;
            }

            List<Float> areaList = new ArrayList<>();
            for (Office office : officeList) {
                float area = office.getArea_value();
                if (areaList.indexOf(area) == -1) {
                    areaList.add(area);
                }
            }
            Collections.sort(areaList);
            StringBuilder areaRange = new StringBuilder();
            areaRange.append(areaList.get(0));
            if (areaList.size() > 1) {
                areaRange.append(" - ")
                        .append(areaList.get(areaList.size() - 1))
                        .append("m²");
            }
            buildingObject.addProperty("area_range", areaRange.toString());
            buildingObject.add("area_list", new Gson().toJsonTree(areaList));
            buildingArray.add(buildingObject);
        }

        json.add("buildingList", buildingArray);
        json.addProperty("checkedDistrictId", district_id);
        json.addProperty("checkedZoneId", zone_id);
        json.addProperty("checkedMetroId", metro_id);
        json.addProperty("checkedTypeId", type_id);
        json.addProperty("checkedAreaRangeId", area_range_id);
        json.addProperty("checkedPriceRangeId", price_range_id);
        json.addProperty("checkedDecorationId", decoration_id);

        int size = officeService.getBuildingSize(searchBean);
        json.addProperty("pageNum", size / PAGE_SIZE);

        json.addProperty("pageIndex", page);
        response.getWriter().write(json.toString());
        response.getWriter().close();
    }

    /**
     * 查询办公室
     * id 办公室id
     * keyword 关键词
     * building_id 商圈id
     * zone_id 区域id
     * metro_id 地铁id
     * type_id 办公室类型id
     * area_range_id 面积区间id
     * price_range_id 价格区间id
     * decoration_id 装修类型id
     */
    @RequestMapping("/offices")
    public void getOfficeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        Long id = null;
        if (request.getParameter("id") != null) {
            id = Long.parseLong(request.getParameter("id"));
        }

        String keyword = null;
        if (request.getParameter("keyword") != null) {
            keyword = request.getParameter("keyword");
        }

        Long building_id = null;
        if (request.getParameter("building_id") != null) {
            building_id = Long.parseLong(request.getParameter("building_id"));
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
        searchBean.setId(id);
        searchBean.setKeyword(keyword);
        searchBean.setBuilding_id(building_id);
        searchBean.setZone_id(zone_id);
        searchBean.setMetro_name(metro);
        searchBean.setType_id(type_id);
        searchBean.setArea_range_id(area_range_id);
        searchBean.setPrice_range_id(price_range_id);
        searchBean.setDecoration_id(decoration_id);

        List<Office> officeList = officeService.getOfficeList(searchBean, page, PAGE_SIZE);
        int size = officeService.getOfficeSize(searchBean);

        JsonObject json = new JsonObject();
        json.add("officeList", new Gson().toJsonTree(officeList));
        json.addProperty("checkedDistrictId", district_id);
        json.addProperty("checkedZoneId", zone_id);
        json.addProperty("checkedMetroId", metro_id);
        json.addProperty("checkedTypeId", type_id);
        json.addProperty("checkedAreaRangeId", area_range_id);
        json.addProperty("checkedPriceRangeId", price_range_id);
        json.addProperty("checkedDecorationId", decoration_id);
        json.addProperty("pageNum", size / PAGE_SIZE);
        json.addProperty("pageIndex", page);
        response.getWriter().write(json.toString());
        response.getWriter().close();
    }
}
