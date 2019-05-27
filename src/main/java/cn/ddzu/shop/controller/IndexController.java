package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.enums.ResultCode;
import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.service.BasicService;
import cn.ddzu.shop.service.OfficeService;
import cn.ddzu.shop.util.JsonUtils;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Autowired
    private OfficeService officeService;
    @Autowired
    private BasicService basicService;

    @RequestMapping("/all")
    public void reset(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("index-all", helper);

        JsonObject json = new JsonObject();
        json.add("bannerList", new Gson().toJsonTree(Collections.singletonList("http://47.96.165.78/images/banner.jpg")));

        List<District> districtList = basicService.getDistrictList();
        json.add("districtList", new Gson().toJsonTree(districtList));

        List<Type> typeList = basicService.getTypeList();
        json.add("typeList", new Gson().toJsonTree(typeList));

        List<AreaRange> areaRangeList = basicService.getAreaRangeList();
        json.add("areaRangeList", new Gson().toJsonTree(areaRangeList));

        List<PriceRange> priceRangeList = basicService.getPriceRangeList();
        json.add("priceRangeList", new Gson().toJsonTree(priceRangeList));

        List<Building> buildingList = officeService.getBuildingListWithOffice(null, 0, 12);
        JsonArray buildingArray = new JsonArray();
        for (Building building : buildingList) {
            JsonObject buildingObject = new Gson().toJsonTree(building).getAsJsonObject();
            buildingObject.add("img_list", JsonUtils.strToStringArray(building.getImg_list()));

            OfficeService.SearchBean officeSearchBean = new OfficeService.SearchBean();
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
        json.add("fineList", buildingArray);
        json.add("coreList", new JsonArray());

        finish(response, ResultCode.SUCCESS, json);
    }

}
