package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.enums.ResultCode;
import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.service.FilterService;
import cn.ddzu.shop.service.NewsService;
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
import java.util.List;

@Controller
@RequestMapping("/filter")
public class FilterController extends BaseController {

    @Autowired
    private FilterService filterService;
    @Autowired
    private NewsService newsService;

    /**
     * 查询所有过滤条件
     * <p>
     * * district_id 区id
     * * zone_id 区域id
     * * metro_id 地铁id
     * * type_id 类型id
     * * area_range_id 面积区间id
     * * price_range_id 价格区间id
     * * decoration_id 装修id
     */
    @RequestMapping("/all")
    public void all(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("filter-all", helper);

        Long district_id = helper.getLong("district_id", 1L);
        Long zone_id = helper.getLong("zone_id", 1L);
        Long metro_id = helper.getLong("metro_id", 1L);
        Long type_id = helper.getLong("type_id", 1L);
        Long area_range_id = helper.getLong("area_range_id", 1L);
        Long price_range_id = helper.getLong("price_range_id", 1L);
        Long decoration_id = helper.getLong("decoration_id", 1L);

        List<District> districtList = filterService.getDistrictList();
        JsonArray districtArray = new JsonArray();
        for (District district : districtList) {
            JsonObject districtObject = new Gson().toJsonTree(district).getAsJsonObject();
            long districtId = district.getId();
            List<Zone> zoneList = filterService.getZoneList(districtId);
            JsonArray zoneArray = new Gson().toJsonTree(zoneList).getAsJsonArray();
            districtObject.add("zoneList", zoneArray);
            districtArray.add(districtObject);
        }

        List<Metro> metroList = filterService.getMetroList();
        List<Type> typeList = filterService.getTypeList();
        List<AreaRange> areaRangeList = filterService.getAreaRangeList();
        List<PriceRange> priceRangeList = filterService.getPriceRangeList();
        List<Decoration> decorationList = filterService.getDecorationList();

        JsonObject json = new JsonObject();
        json.add("districtList", districtArray);
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

        finish(response, ResultCode.SUCCESS, json);
    }

    /**
     * 查询办公室过滤条件
     * <p>
     * building_id 楼id
     * type_id 类型id
     * area_range_id 面积区间id
     * price_range_id 价格区间id
     * decoration_id 装修id
     */
    @RequestMapping("/office")
    public void office(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("filter-office", helper);

        Long building_id = helper.getLong("building_id");
        Long type_id = helper.getLong("type_id", 1L);
        Long area_range_id = helper.getLong("area_range_id", 1L);
        Long price_range_id = helper.getLong("price_range_id", 1L);
        Long decoration_id = helper.getLong("decoration_id", 1L);

        if (building_id == null) {
            finish(response, ResultCode.ERROR_PARAM_EMPTY);
            return;
        }

        List<Type> typeList = filterService.getTypeList(building_id);
        List<AreaRange> areaRangeList = filterService.getAreaRangeList(building_id);
        List<PriceRange> priceRangeList = filterService.getPriceRangeList(building_id);
        List<Decoration> decorationList = filterService.getDecorationList(building_id);

        JsonObject json = new JsonObject();
        json.add("typeList", new Gson().toJsonTree(typeList));
        json.add("areaRangeList", new Gson().toJsonTree(areaRangeList));
        json.add("priceRangeList", new Gson().toJsonTree(priceRangeList));
        json.add("decorationList", new Gson().toJsonTree(decorationList));
        json.addProperty("checkedTypeId", type_id);
        json.addProperty("checkedAreaRangeId", area_range_id);
        json.addProperty("checkedPriceRangeId", price_range_id);
        json.addProperty("checkedDecorationId", decoration_id);

        finish(response, ResultCode.SUCCESS, json);
    }

    /**
     * 查询新闻标签
     * <p>
     * news_tag_id 新闻标签
     */
    @RequestMapping("/newsTag")
    public void newsTag(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("filter-newsTag", helper);

        Long news_tag_id = helper.getLong("news_tag_id", 1L);

        List<NewsTag> newsTagList = newsService.getNewsTagList();

        JsonObject json = new JsonObject();
        json.add("newsTagList", new Gson().toJsonTree(newsTagList));
        json.addProperty("checkedNewsTagId", news_tag_id);

        finish(response, ResultCode.SUCCESS, json);
    }
}
