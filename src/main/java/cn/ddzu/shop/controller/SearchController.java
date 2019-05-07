package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.service.BasicService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
public class SearchController {

    @Autowired
    private BasicService basicService;

    @RequestMapping("/districts")
    public void getDistrictList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        List<District> districtList = basicService.getDistrictList();
        for (District district : districtList) {
            long district_id = district.getId();
            List<Zone> zoneList = basicService.getZoneList(district_id);
            district.setZoneList(zoneList);
        }

        List<Metro> metroList = basicService.getMetroList();
        List<Type> typeList = basicService.getTypeList();
        List<AreaRange> areaRangeList = basicService.getAreaRangeList();
        List<PriceRange> priceRanges = basicService.getPriceRangeList();
        List<Decoration> decorationList = basicService.getDecorationList();

        JsonObject json = new JsonObject();
        json.add("districtList", new Gson().toJsonTree(districtList));
        json.add("metroList", new Gson().toJsonTree(metroList));
        json.add("typeList", new Gson().toJsonTree(typeList));
        json.add("areaRangeList", new Gson().toJsonTree(areaRangeList));
        json.add("priceRanges", new Gson().toJsonTree(priceRanges));
        json.add("decorationList", new Gson().toJsonTree(decorationList));
        json.addProperty("checkedDistrictId", 2);
        json.addProperty("checkedZoneId", 1);

        response.getWriter().write(json.toString());
        response.getWriter().close();
    }

    @RequestMapping("/addOffice")
    public void addOffice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String name = null;
        if (request.getParameter("name") != null) {
            name = request.getParameter("name");
        }

        Long business_center_id = null;
        if (request.getParameter("business_center_id") != null) {
            business_center_id = Long.parseLong(request.getParameter("business_center_id"));
        }

        Long zone_id = null;
        if (request.getParameter("zone_id") != null) {
            zone_id = Long.parseLong(request.getParameter("zone_id"));
        }

        String address = null;
        if (request.getParameter("address") != null) {
            address = request.getParameter("address");
        }

        List<Long> metro_list = new ArrayList<>();
        if (request.getParameter("metro_list") != null) {
            String metro_list_str = request.getParameter("metro_list");
            metro_list = new Gson().fromJson(metro_list_str, new TypeToken<List<Long>>() {
            }.getType());
        }

        Long type_id = null;
        if (request.getParameter("type_id") != null) {
            type_id = Long.parseLong(request.getParameter("type_id"));
        }

        Float area_value = null;
        if (request.getParameter("area_value") != null) {
            area_value = Float.valueOf(request.getParameter("area_value"));
        }

        Float price = null;
        if (request.getParameter("price") != null) {
            price = Float.valueOf(request.getParameter("price"));
        }

        Long decoration_id = null;
        if (request.getParameter("decoration_id") != null) {
            decoration_id = Long.parseLong(request.getParameter("decoration_id"));
        }

        List<String> metro_name_list = new ArrayList<>();
        for (Long id : metro_list) {
            Metro metro = basicService.getMetro(id);
            if (metro != null) {
                metro_name_list.add(metro.getName());
            }
        }

        long area_range_id;
        if (area_value == null) {
            area_range_id = 1L;
        } else if (area_value > 0 && area_value <= 100) {
            area_range_id = 2L;
        } else if (area_value > 100 && area_value <= 300) {
            area_range_id = 3L;
        } else if (area_value > 300 && area_value <= 500) {
            area_range_id = 4L;
        } else if (area_value > 500 && area_value <= 1000) {
            area_range_id = 5L;
        } else if (area_value > 1000) {
            area_range_id = 6L;
        } else {
            area_range_id = 1L;
        }

        long price_range_id;
        if (price == null) {
            price_range_id = 1L;
        } else if (price > 0 && price <= 3) {
            price_range_id = 2L;
        } else if (price > 3 && price <= 4) {
            price_range_id = 3L;
        } else if (price > 4 && price <= 5) {
            price_range_id = 4L;
        } else if (price > 5 && price <= 7) {
            price_range_id = 5L;
        } else if (price > 7 && price <= 9) {
            price_range_id = 6L;
        } else if (price > 9 && price <= 12) {
            price_range_id = 7L;
        } else if (price > 12) {
            price_range_id = 8L;
        } else {
            price_range_id = 1L;
        }

        Office office = new Office();
        office.setName(name);
        office.setBusiness_center_id(business_center_id);
        office.setZone_id(zone_id);
        office.setAddress(address);
        office.setMetro_name_list(new Gson().toJson(metro_name_list));
        office.setType_id(type_id);
        office.setArea_value(area_value);
        office.setArea_range_id(area_range_id);
        office.setPrice(price);
        office.setPrice_range_id(price_range_id);
        office.setDecoration_id(decoration_id);

        basicService.addOffice(office);

        JsonObject json = new JsonObject();
        json.addProperty("resultCode", 1);
        response.getWriter().write(json.toString());
        response.getWriter().close();
    }

    @RequestMapping("/offices")
    public void getOfficeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        List<Office> officeList = basicService.getOfficeList(
                null
                , null
                , null
                , null
                , null
                , null
                , null
                , null
                , null
        );

        JsonObject json = new JsonObject();
        json.add("officeList", new Gson().toJsonTree(officeList));
        response.getWriter().write(json.toString());
        response.getWriter().close();
    }
}
