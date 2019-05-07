package cn.ddzu.shop.service.impl;

import cn.ddzu.shop.dao.*;
import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private OfficeMapperDao officeMapperDao;

    @Override
    public List<District> getDistrictList() {
        return districtMapperDao.selectDistrictList();
    }

    @Override
    public List<Zone> getZoneList(Long district_id) {
        return zoneMapperDao.selectZoneList(district_id);
    }

    @Override
    public List<Metro> getMetroList() {
        return metroMapperDao.selectMetroList();
    }

    @Override
    public Metro getMetro(Long id) {
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
    public List<PriceRange> getPriceRangeList() {
        return priceRangeMapperDao.selectPriceRangeList();
    }

    @Override
    public List<Decoration> getDecorationList() {
        return decorationMapperDao.selectDecorationList();
    }

    @Override
    public void addOffice(Office office) {
        officeMapperDao.insertOffice(office);
    }

    @Override
    public List<Office> getOfficeList(Long id
            , String keyword
            , Long business_center_id
            , Long zone_id
            , String metro_name
            , Long type_id
            , Long area_range_id
            , Long price_range_id
            , Long decoration_id) {
        return officeMapperDao.selectOfficeList(id
                , keyword
                , business_center_id
                , zone_id
                , metro_name
                , type_id
                , area_range_id
                , price_range_id
                , decoration_id);
    }
}
