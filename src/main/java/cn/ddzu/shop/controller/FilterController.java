package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.service.BasicService;
import cn.ddzu.shop.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/filter")
public class FilterController {

    @Autowired
    private BasicService basicService;

    /**
     * 查询所有过滤条件
     */
    @RequestMapping("/all")
    public void getAllFilter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        Log.d("filter-all", new Gson().toJson(request.getParameterMap()));

        long district_id = 1L;
        if (request.getParameter("district_id") != null) {
            district_id = Long.parseLong(request.getParameter("district_id"));
        }

        long zone_id = 1L;
        if (request.getParameter("zone_id") != null) {
            zone_id = Long.parseLong(request.getParameter("zone_id"));
        }

        long metro_id = 1L;
        if (request.getParameter("metro_id") != null) {
            metro_id = Long.parseLong(request.getParameter("metro_id"));
        }

        long type_id = 1L;
        if (request.getParameter("type_id") != null) {
            type_id = Long.parseLong(request.getParameter("type_id"));
        }

        long area_range_id = 1L;
        if (request.getParameter("area_range_id") != null) {
            area_range_id = Long.parseLong(request.getParameter("area_range_id"));
        }

        long price_range_id = 1L;
        if (request.getParameter("price_range_id") != null) {
            price_range_id = Long.parseLong(request.getParameter("price_range_id"));
        }

        long decoration_id = 1L;
        if (request.getParameter("decoration_id") != null) {
            decoration_id = Long.parseLong(request.getParameter("decoration_id"));
        }

        List<District> districtList = basicService.getDistrictList();
        for (District district : districtList) {
            long districtId = district.getId();
            List<Zone> zoneList = basicService.getZoneList(districtId);
            district.setZoneList(zoneList);
        }

        List<Metro> metroList = basicService.getMetroList();
        List<Type> typeList = basicService.getTypeList();
        List<AreaRange> areaRangeList = basicService.getAreaRangeList();
        List<PriceRange> priceRangeList = basicService.getPriceRangeList();
        List<Decoration> decorationList = basicService.getDecorationList();

        JsonObject json = new JsonObject();
        json.add("districtList", new Gson().toJsonTree(districtList));
        json.add("metroList", new Gson().toJsonTree(metroList));
        json.add("typeList", new Gson().toJsonTree(typeList));
        json.add("areaRangeList", new Gson().toJsonTree(areaRangeList));
        json.add("priceRangeList", new Gson().toJsonTree(priceRangeList));
        json.add("decorationList", new Gson().toJsonTree(decorationList));
        json.addProperty("checkedDistrictId", district_id);
        json.addProperty("checkedZoneId", zone_id);
        json.addProperty("checkedMetroId", metro_id);
        json.addProperty("checkedTypeId", type_id);
        json.addProperty("checkedAreaRangeId", area_range_id);
        json.addProperty("checkedPriceRangeId", price_range_id);
        json.addProperty("checkedDecorationId", decoration_id);

        response.getWriter().write(json.toString());
        response.getWriter().close();
    }

    /**
     * 查询办公室过滤条件
     */
    @RequestMapping("/office")
    public void getOfficeFilter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        Log.d("filter-office", new Gson().toJson(request.getParameterMap()));

        Long building_id = null;
        if (request.getParameter("building_id") != null) {
            building_id = Long.parseLong(request.getParameter("building_id"));
        }

        long type_id = 1L;
        if (request.getParameter("type_id") != null) {
            type_id = Long.parseLong(request.getParameter("type_id"));
        }

        long area_range_id = 1L;
        if (request.getParameter("area_range_id") != null) {
            area_range_id = Long.parseLong(request.getParameter("area_range_id"));
        }

        long price_range_id = 1L;
        if (request.getParameter("price_range_id") != null) {
            price_range_id = Long.parseLong(request.getParameter("price_range_id"));
        }

        long decoration_id = 1L;
        if (request.getParameter("decoration_id") != null) {
            decoration_id = Long.parseLong(request.getParameter("decoration_id"));
        }

        List<Type> typeList = basicService.getTypeList(building_id);
        List<AreaRange> areaRangeList = basicService.getAreaRangeList(building_id);
        List<PriceRange> priceRangeList = basicService.getPriceRangeList(building_id);
        List<Decoration> decorationList = basicService.getDecorationList(building_id);

        JsonObject json = new JsonObject();
        json.add("typeList", new Gson().toJsonTree(typeList));
        json.add("areaRangeList", new Gson().toJsonTree(areaRangeList));
        json.add("priceRangeList", new Gson().toJsonTree(priceRangeList));
        json.add("decorationList", new Gson().toJsonTree(decorationList));
        json.addProperty("checkedTypeId", type_id);
        json.addProperty("checkedAreaRangeId", area_range_id);
        json.addProperty("checkedPriceRangeId", price_range_id);
        json.addProperty("checkedDecorationId", decoration_id);

        response.getWriter().write(json.toString());
        response.getWriter().close();
    }
}
