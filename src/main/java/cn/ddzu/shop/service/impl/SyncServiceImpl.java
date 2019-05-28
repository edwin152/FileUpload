package cn.ddzu.shop.service.impl;

import cn.ddzu.shop.dao.*;
import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SyncServiceImpl implements SyncService {

    @Autowired
    private AreaRangeMapperDao areaRangeMapperDao;
    @Autowired
    private BuildingMapperDao buildingMapperDao;
    @Autowired
    private DecorationMapperDao decorationMapperDao;
    @Autowired
    private DistrictMapperDao districtMapperDao;
    @Autowired
    private MetroMapperDao metroMapperDao;
    @Autowired
    private NewsMapperDao newsMapperDao;
    @Autowired
    private NewsTagMapperDao newsTagMapperDao;
    @Autowired
    private OfficeMapperDao officeMapperDao;
    @Autowired
    private PriceRangeMapperDao priceRangeMapperDao;
    @Autowired
    private TypeMapperDao typeMapperDao;
    @Autowired
    private UserMapperDao userMapperDao;
    @Autowired
    private ZoneMapperDao zoneMapperDao;

    @Override
    public Map<String, List> getAll() {
        Map<String, List> map = new HashMap<>();
        map.put("area_range", areaRangeMapperDao.selectAll());
        map.put("building", buildingMapperDao.selectAll());
        map.put("district", districtMapperDao.selectAll());
        map.put("decoration", decorationMapperDao.selectAll());
        map.put("metro", metroMapperDao.selectAll());
        map.put("news", newsMapperDao.selectAll());
        map.put("news_tag", newsTagMapperDao.selectAll());
        map.put("office", officeMapperDao.selectAll());
        map.put("price_range", priceRangeMapperDao.selectAll());
        map.put("type", typeMapperDao.selectAll());
        map.put("user", userMapperDao.selectAll());
        map.put("zone", zoneMapperDao.selectAll());
        return map;
    }

    @Override
    public void setAll(List<AreaRange> areaRangeList
            , List<Building> buildingList
            , List<Decoration> decorationList
            , List<District> districtList
            , List<Metro> metroList
            , List<News> newsList
            , List<NewsTag> newsTagList
            , List<Office> officeList
            , List<PriceRange> priceRangeList
            , List<Type> typeList
            , List<User> userList
            , List<Zone> zoneList) {

        areaRangeMapperDao.drop();
        areaRangeMapperDao.create();
        for (AreaRange areaRange : areaRangeList) {
            areaRangeMapperDao.insert(areaRange);
        }

        buildingMapperDao.drop();
        buildingMapperDao.create();
        for (Building building : buildingList) {
            buildingMapperDao.insert(building);
        }

        decorationMapperDao.drop();
        decorationMapperDao.create();
        for (Decoration decoration : decorationList) {
            decorationMapperDao.insert(decoration);
        }

        districtMapperDao.drop();
        districtMapperDao.create();
        for (District district : districtList) {
            districtMapperDao.insert(district);
        }

        metroMapperDao.drop();
        metroMapperDao.create();
        for (Metro metro : metroList) {
            metroMapperDao.insert(metro);
        }

        newsMapperDao.drop();
        newsMapperDao.create();
        for (News news : newsList) {
            newsMapperDao.insert(news);
        }

        newsTagMapperDao.drop();
        newsTagMapperDao.create();
        for (NewsTag newsTag : newsTagList) {
            newsTagMapperDao.insert(newsTag);
        }

        officeMapperDao.drop();
        officeMapperDao.create();
        for (Office office : officeList) {
            officeMapperDao.insert(office);
        }

        priceRangeMapperDao.drop();
        priceRangeMapperDao.create();
        for (PriceRange priceRange : priceRangeList) {
            priceRangeMapperDao.insert(priceRange);
        }

        typeMapperDao.drop();
        typeMapperDao.create();
        for (Type type : typeList) {
            typeMapperDao.insert(type);
        }

        userMapperDao.drop();
        userMapperDao.create();
        for (User user : userList) {
            userMapperDao.insert(user);
        }

        zoneMapperDao.drop();
        zoneMapperDao.create();
        for (Zone zone : zoneList) {
            zoneMapperDao.insert(zone);
        }
    }
}
