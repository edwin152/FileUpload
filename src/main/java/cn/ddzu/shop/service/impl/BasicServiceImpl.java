package cn.ddzu.shop.service.impl;

import cn.ddzu.shop.dao.*;
import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.service.BasicService;
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
    public List<District> getDistrictList() {
        return districtMapperDao.selectDistrictList();
    }

    @Override
    public List<Zone> getZoneList(Long district_id) {
        if (district_id == null) {
            return new ArrayList<>();
        }
        return zoneMapperDao.selectZoneList(district_id);
    }

    @Override
    public Zone getZone(Long id) {
        if (id == null) {
            return null;
        }
        return zoneMapperDao.selectZoneById(id);
    }

    @Override
    public List<Metro> getMetroList() {
        return metroMapperDao.selectMetroList();
    }

    @Override
    public Metro getMetro(Long id) {
        if (id == null) {
            return null;
        }
        return metroMapperDao.selectMetroById(id);
    }

    @Override
    public List<Type> getTypeList() {
        return typeMapperDao.selectTypeList();
    }

    @Override
    public List<AreaRange> getAreaRangeList() {
        return areaRangeMapperDao.selectAreaRangeList();
    }

    @Override
    public List<AreaRange> getAreaRangeList(Long building_id) {
        if (building_id == null) {
            return new ArrayList<>();
        }
        return areaRangeMapperDao.selectAreaRangeListByBuilding(building_id);
    }

    @Override
    public List<PriceRange> getPriceRangeList() {
        return priceRangeMapperDao.selectPriceRangeList();
    }

    @Override
    public List<PriceRange> getPriceRangeList(Long building_id) {
        if (building_id == null) {
            return new ArrayList<>();
        }
        return priceRangeMapperDao.selectPriceRangeListByBuilding(building_id);
    }

    @Override
    public List<Decoration> getDecorationList() {
        return decorationMapperDao.selectDecorationList();
    }
}
