package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.service.BasicService;
import cn.ddzu.shop.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
    private BasicService basicService;

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
    public void getAllFilter(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        List<District> districtList = basicService.getDistrictList();
        JsonArray districtArray = new JsonArray();
        for (District district : districtList) {
            JsonObject districtObject = new Gson().toJsonTree(district).getAsJsonObject();
            long districtId = district.getId();
            List<Zone> zoneList = basicService.getZoneList(districtId);
            JsonArray zoneArray = new Gson().toJsonTree(zoneList).getAsJsonArray();
            districtObject.add("zone_list", zoneArray);
            districtArray.add(districtObject);
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

        finish(response, SUCCESS_MESSAGE, json);
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
    public void getOfficeFilter(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            Message error = new Message(1001, "building_id为空");
            finish(response, error);
            return;
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

        finish(response, SUCCESS_MESSAGE, json);
    }
}
