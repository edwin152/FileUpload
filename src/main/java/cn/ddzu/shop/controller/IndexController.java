package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.enums.ResultCode;
import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.service.FilterService;
import cn.ddzu.shop.service.NewsService;
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
import java.util.*;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Autowired
    private OfficeService officeService;
    @Autowired
    private FilterService filterService;
    @Autowired
    private NewsService newsService;

    @RequestMapping("/all")
    public void all(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("index-all", helper);

        JsonObject json = new JsonObject();
        json.add("bannerList", new Gson().toJsonTree(Collections.singletonList("http://www.orangeban.com/images/banner.jpg")));

        List<District> districtList = filterService.getDistrictList();
        json.add("districtList", new Gson().toJsonTree(districtList));

        List<Type> typeList = filterService.getTypeList();
        json.add("typeList", new Gson().toJsonTree(typeList));

        List<AreaRange> areaRangeList = filterService.getAreaRangeList();
        json.add("areaRangeList", new Gson().toJsonTree(areaRangeList));

        List<PriceRange> priceRangeList = filterService.getPriceRangeList();
        json.add("priceRangeList", new Gson().toJsonTree(priceRangeList));

        List<Building> buildingList = officeService.getIndexBuilding();
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

        List<Zone> zoneList = filterService.getCoreZoneList();
        zoneList.sort(new Comparator<Zone>() {
            @Override
            public int compare(Zone o1, Zone o2) {
                if (o1.getId() == 24) return -1;
                if (o2.getId() == 24) return 1;

                if (o1.getId() == 40) return -1;
                if (o2.getId() == 40) return 1;

                if (o1.getId() == 35) return -1;
                if (o2.getId() == 35) return 1;

                if (o1.getId() == 6) return -1;
                if (o2.getId() == 6) return 1;

                if (o1.getId() == 73) return -1;
                if (o2.getId() == 73) return 1;

                return Long.compare(o1.getId(), o2.getId());
            }
        });
        JsonArray zoneArray = new JsonArray();
        for (Zone zone : zoneList) {
            JsonObject zoneObject = new Gson().toJsonTree(zone).getAsJsonObject();
            zoneObject.add("img_list", JsonUtils.strToStringArray(zone.getImg_list()));
            if (zone.getId() == zone.getDistrict_id()) {
                zoneObject.addProperty("name", zone.getDistrict_name());
            }
            zoneArray.add(zoneObject);
        }
        json.add("coreList", zoneArray);

        List<Office> newOfficeList = officeService.getNewOffice(1);
        if (!newOfficeList.isEmpty()) {
            JsonObject todaySupport = new JsonObject();
            json.add("todaySupport", todaySupport);

            Office office = newOfficeList.get(0);
            JsonObject officeObject = new Gson().toJsonTree(office).getAsJsonObject();
            officeObject.add("img_list", JsonUtils.strToStringArray(office.getImg_list()));
            todaySupport.add("office", officeObject);

            Long building_id = office.getBuilding_id();
            Building building = officeService.getBuilding(building_id);
            JsonObject buildingObject = new Gson().toJsonTree(building).getAsJsonObject();
            buildingObject.add("img_list", JsonUtils.strToStringArray(building.getImg_list()));
            todaySupport.add("building", buildingObject);
        }

        List<News> newsList = newsService.getIndexNews();
        JsonArray newsArray = new JsonArray();
        for (News news : newsList) {
            String content = news.getContent();
            if (content != null) {
                content = content.replaceAll("</?.+?/?>", "");
                if (content.length() > 200) {
                    content = content.substring(0, 200);
                    content = content + "...";
                }
                news.setContent(content);
            }
            JsonObject newsObject = new Gson().toJsonTree(news).getAsJsonObject();
            newsObject.add("img_list", JsonUtils.strToStringArray(news.getImg_list()));
            newsArray.add(newsObject);
        }
        json.add("newsList", newsArray);

        finish(response, ResultCode.SUCCESS, json);
    }
}