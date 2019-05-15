package cn.ddzu.shop.service.impl;

import cn.ddzu.shop.dao.*;
import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.service.BasicService;
import cn.ddzu.shop.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicServiceImpl implements BasicService {

    @Autowired
    private DistrictMapperDao districtMapperDao;
    @Autowired
    private ZoneMapperDao zoneMapperDao;
    @Autowired
    private MetroMapperDao metroMapperDao;
    @Autowired
    private TypeMapperDao typeMapperDao;
    @Autowired
    private AreaRangeMapperDao areaRangeMapperDao;
    @Autowired
    private PriceRangeMapperDao priceRangeMapperDao;
    @Autowired
    private DecorationMapperDao decorationMapperDao;

    @Override
    public void reset() {
        Log.d("start reset database");
        districtMapperDao.drop();
        districtMapperDao.create();
        districtMapperDao.init();

        zoneMapperDao.drop();
        zoneMapperDao.create();
        zoneMapperDao.init();

        metroMapperDao.drop();
        metroMapperDao.create();
        metroMapperDao.init();

        typeMapperDao.drop();
        typeMapperDao.create();
        typeMapperDao.init();

        areaRangeMapperDao.drop();
        areaRangeMapperDao.create();
        areaRangeMapperDao.init();

        priceRangeMapperDao.drop();
        priceRangeMapperDao.create();
        priceRangeMapperDao.init();

        decorationMapperDao.drop();
        decorationMapperDao.create();
        decorationMapperDao.init();
    }

    @Override
    public List<District> getDistrictList() {
        Log.d("start get district list");
        return districtMapperDao.selectDistrictList();
    }

    @Override
    public List<Zone> getZoneList(Long district_id) {
        if (district_id == null) {
            return new ArrayList<>();
        }
        Log.d("start get zone list");
        return zoneMapperDao.selectZoneList(district_id);
    }

    @Override
    public Zone getZone(Long id) {
        if (id == null) {
            return null;
        }
        Log.d("start get zone list by id : " + id);
        return zoneMapperDao.selectZoneById(id);
    }

    @Override
    public List<Metro> getMetroList() {
        Log.d("start get metro list");
        return metroMapperDao.selectMetroList();
    }

    @Override
    public Metro getMetro(Long id) {
        if (id == null) {
            return null;
        }
        Log.d("start get metro list by id : " + id);
        return metroMapperDao.selectMetroById(id);
    }

    @Override
    public List<Type> getTypeList() {
        Log.d("start get type list");
        return typeMapperDao.selectTypeList();
    }

    @Override
    public List<Type> getTypeList(Long building_id) {
        if (building_id == null) {
            return new ArrayList<>();
        }
        Log.d("start get type list by building_id : " + building_id);
        return typeMapperDao.selectTypeListByBuilding(building_id);
    }

    @Override
    public List<AreaRange> getAreaRangeList() {
        Log.d("start get area range list");
        return areaRangeMapperDao.selectAreaRangeList();
    }

    @Override
    public List<AreaRange> getAreaRangeList(Long building_id) {
        if (building_id == null) {
            return new ArrayList<>();
        }
        Log.d("start get area range list by building_id : " + building_id);
        return areaRangeMapperDao.selectAreaRangeListByBuilding(building_id);
    }

    @Override
    public List<PriceRange> getPriceRangeList() {
        Log.d("start get price range list");
        return priceRangeMapperDao.selectPriceRangeList();
    }

    @Override
    public List<PriceRange> getPriceRangeList(Long building_id) {
        if (building_id == null) {
            return new ArrayList<>();
        }
        Log.d("start get price range list by building_id : " + building_id);
        return priceRangeMapperDao.selectPriceRangeListByBuilding(building_id);
    }

    @Override
    public List<Decoration> getDecorationList() {
        Log.d("start get decoration list");
        return decorationMapperDao.selectDecorationList();
    }

    @Override
    public List<Decoration> getDecorationList(Long building_id) {
        if (building_id == null) {
            return new ArrayList<>();
        }
        Log.d("start get decoration list by building_id : " + building_id);
        return decorationMapperDao.selectDecorationListByBuilding(building_id);
    }
}
