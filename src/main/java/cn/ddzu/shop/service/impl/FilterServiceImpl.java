package cn.ddzu.shop.service.impl;

import cn.ddzu.shop.dao.*;
import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.service.FilterService;
import cn.ddzu.shop.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {

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
        Log.d("start get district list");
        return districtMapperDao.select();
    }

    @Override
    public List<Zone> getZoneList(Long district_id) {
        if (district_id == null) {
            return new ArrayList<>();
        }
        Log.d("start get zone list");
        return zoneMapperDao.selectByDistrict(district_id);
    }

    @Override
    public List<Zone> getCoreZoneList() {
        return zoneMapperDao.selectCenterZone();
    }

    @Override
    public void updateZone(Zone zone) {
        zoneMapperDao.update(zone);
    }

    @Override
    public Zone getZone(Long id) {
        if (id == null) {
            return null;
        }
        Log.d("start get zone list by id : " + id);
        return zoneMapperDao.selectById(id);
    }

    @Override
    public List<Metro> getMetroList() {
        Log.d("start get metro list");
        return metroMapperDao.select();
    }

    @Override
    public Metro getMetro(Long id) {
        if (id == null) {
            return null;
        }
        Log.d("start get metro list by id : " + id);
        return metroMapperDao.selectById(id);
    }

    @Override
    public List<Type> getTypeList() {
        Log.d("start get type list");
        return typeMapperDao.select();
    }

    @Override
    public List<Type> getTypeList(Long building_id) {
        if (building_id == null) {
            return new ArrayList<>();
        }
        Log.d("start get type list by building_id : " + building_id);
        return typeMapperDao.selectByBuilding(building_id);
    }

    @Override
    public List<AreaRange> getAreaRangeList() {
        Log.d("start get area range list");
        return areaRangeMapperDao.select();
    }

    @Override
    public List<AreaRange> getAreaRangeList(Long building_id) {
        if (building_id == null) {
            return new ArrayList<>();
        }
        Log.d("start get area range list by building_id : " + building_id);
        return areaRangeMapperDao.selectByBuilding(building_id);
    }

    @Override
    public Long getAreaRangeId(Float area) {
        Long area_range_id;
        if (area == null) {
            area_range_id = null;
        } else if (area > 0 && area <= 100) {
            area_range_id = 2L;
        } else if (area > 100 && area <= 300) {
            area_range_id = 3L;
        } else if (area > 300 && area <= 500) {
            area_range_id = 4L;
        } else if (area > 500 && area <= 1000) {
            area_range_id = 5L;
        } else if (area > 1000) {
            area_range_id = 6L;
        } else {
            area_range_id = null;
        }
        return area_range_id;
    }

    @Override
    public List<PriceRange> getPriceRangeList() {
        Log.d("start get price range list");
        return priceRangeMapperDao.select();
    }

    @Override
    public List<PriceRange> getPriceRangeList(Long building_id) {
        if (building_id == null) {
            return new ArrayList<>();
        }
        Log.d("start get price range list by building_id : " + building_id);
        return priceRangeMapperDao.selectByBuilding(building_id);
    }

    @Override
    public Long getPriceRangeId(Float price) {
        Long price_range_id;
        if (price == null) {
            price_range_id = null;
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
            price_range_id = null;
        }
        return price_range_id;
    }

    @Override
    public List<Decoration> getDecorationList() {
        Log.d("start get decoration list");
        return decorationMapperDao.select();
    }

    @Override
    public List<Decoration> getDecorationList(Long building_id) {
        if (building_id == null) {
            return new ArrayList<>();
        }
        Log.d("start get decoration list by building_id : " + building_id);
        return decorationMapperDao.selectByBuilding(building_id);
    }
}
