package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.Metro;
import cn.ddzu.shop.entity.Office;
import cn.ddzu.shop.entity.Zone;
import cn.ddzu.shop.service.BasicService;
import cn.ddzu.shop.service.OfficeService;
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
@RequestMapping("/search")
public class SearchController {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private OfficeService officeService;
    @Autowired
    private BasicService basicService;

    /**
     * 查询办公室
     * id 办公室id
     * keyword 关键词
     * business_center_id 商圈id
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

        Long business_center_id = null;
        if (request.getParameter("business_center_id") != null) {
            business_center_id = Long.parseLong(request.getParameter("business_center_id"));
        }

        Long district_id = null;
        if (request.getParameter("district_id") != null) {
            district_id = Long.parseLong(request.getParameter("district_id"));
        }

        Long zone_id = null;
        if (request.getParameter("zone_id") != null) {
            zone_id = Long.parseLong(request.getParameter("zone_id"));
        }

        Long metro_id = null;
        if (request.getParameter("metro_id") != null) {
            metro_id = Long.parseLong(request.getParameter("metro_id"));
        }

        Long type_id = null;
        if (request.getParameter("type_id") != null) {
            type_id = Long.parseLong(request.getParameter("type_id"));
        }

        Long area_range_id = null;
        if (request.getParameter("area_range_id") != null) {
            area_range_id = Long.parseLong(request.getParameter("area_range_id"));
        }

        Long price_range_id = null;
        if (request.getParameter("price_range_id") != null) {
            price_range_id = Long.parseLong(request.getParameter("price_range_id"));
        }

        Long decoration_id = null;
        if (request.getParameter("decoration_id") != null) {
            decoration_id = Long.parseLong(request.getParameter("decoration_id"));
        }

        int page = 0;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        Metro metro = basicService.getMetro(metro_id);

        Long sqlZoneId = zone_id != null && zone_id.equals(district_id) ? null : zone_id;
        List<Office> officeList = officeService.getOfficeList(id
                , keyword
                , business_center_id
                , sqlZoneId
                , metro == null ? null : metro.getName()
                , type_id
                , area_range_id
                , price_range_id
                , decoration_id
                , page
                , PAGE_SIZE
        );
        Integer size = officeService.getOfficeSize(id
                , keyword
                , business_center_id
                , sqlZoneId
                , metro == null ? null : metro.getName()
                , type_id
                , area_range_id
                , price_range_id
                , decoration_id
        );
        if (size == null) size = 0;

        if (district_id == null) {
            district_id = 1L;
        }

        Zone zone = basicService.getZone(zone_id);
        if (zone_id == null || zone == null || zone.getDistrict_id() == district_id) {
            zone_id = district_id;
        }

        if (metro_id == null) {
            metro_id = 1L;
        }

        if (type_id == null) {
            type_id = 1L;
        }

        if (area_range_id == null) {
            area_range_id = 1L;
        }

        if (price_range_id == null) {
            price_range_id = 1L;
        }

        if (decoration_id == null) {
            decoration_id = 1L;
        }

        JsonObject json = new JsonObject();
        json.add("officeList", new Gson().toJsonTree(officeList));
        json.addProperty("checkedDistrictId", district_id);
        json.addProperty("checkedZoneId", zone_id);
        json.addProperty("checkedMetroId", metro_id);
        json.addProperty("checkedTypeId", type_id);
        json.addProperty("checkedAreaRangeId", area_range_id);
        json.addProperty("checkedPriceRangeId", price_range_id);
        json.addProperty("checkedDecorationId", decoration_id);
        json.addProperty("pageNum", size / PAGE_SIZE + 1);
        json.addProperty("pageIndex", page);
        response.getWriter().write(json.toString());
        response.getWriter().close();
    }
}
