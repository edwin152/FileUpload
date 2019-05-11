package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.Building;
import cn.ddzu.shop.entity.Metro;
import cn.ddzu.shop.entity.Office;
import cn.ddzu.shop.service.BasicService;
import cn.ddzu.shop.service.OfficeService;
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
@RequestMapping("/edit")
public class EditController {

    @Autowired
    private BasicService basicService;
    @Autowired
    private OfficeService officeService;

    /**
     * 新增楼
     * name 名字
     * zone_id 区域id
     * address 地址
     * metro_list 地铁集合[2,4,6]
     * decoration_id 装修类型id
     */
    @RequestMapping("/addBuilding")
    public void addBuilding(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String name = null;
        if (request.getParameter("name") != null) {
            name = request.getParameter("name");
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

        String img_list = null;
        if (request.getParameter("img_list") != null) {
            img_list = request.getParameter("img_list");
        }

        List<String> metro_name_list = new ArrayList<>();
        for (Long id : metro_list) {
            Metro metro = basicService.getMetro(id);
            if (metro != null) {
                metro_name_list.add(metro.getName());
            }
        }

        Building building = new Building(name
                , zone_id
                , address
                , new Gson().toJson(metro_name_list)
                , img_list);

        officeService.addBuilding(building);

        JsonObject json = new JsonObject();
        json.addProperty("resultCode", 1);
        response.getWriter().write(json.toString());
        response.getWriter().close();
    }

    /**
     * 新增办公室
     * name 名字
     * building_id 商圈id
     * address 地址
     * type_id 办公室类型id
     * area_value 面积
     * price 单平米价格
     * decoration_id 装修类型id
     */
    @RequestMapping("/addOffice")
    public void addOffice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String name = null;
        if (request.getParameter("name") != null) {
            name = request.getParameter("name");
        }

        Long building_id = null;
        if (request.getParameter("building_id") != null) {
            building_id = Long.parseLong(request.getParameter("building_id"));
        }

        String address = null;
        if (request.getParameter("address") != null) {
            address = request.getParameter("address");
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

        String img_list = null;
        if (request.getParameter("img_list") != null) {
            img_list = request.getParameter("img_list");
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

        Office office = new Office(name
                , building_id
                , address
                , type_id
                , area_value
                , area_range_id
                , price
                , price_range_id
                , decoration_id
                , img_list);

        officeService.addOffice(office);

        JsonObject json = new JsonObject();
        json.addProperty("resultCode", 1);
        response.getWriter().write(json.toString());
        response.getWriter().close();
    }
}
