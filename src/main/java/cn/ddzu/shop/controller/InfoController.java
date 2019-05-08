package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.service.BasicService;
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
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private BasicService basicService;

    /**
     * 查询过滤条件
     */
    @RequestMapping("/filters")
    public void getFilterList(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        json.addProperty("checkedDistrictId", 1);
        json.addProperty("checkedZoneId", 1);
        json.addProperty("checkedMetroId", 1);
        json.addProperty("checkedTypeId", 1);
        json.addProperty("checkedAreaRangeId", 1);
        json.addProperty("checkedPriceRangeId", 1);
        json.addProperty("checkedDecorationId", 1);

        response.getWriter().write(json.toString());
        response.getWriter().close();
    }
}
