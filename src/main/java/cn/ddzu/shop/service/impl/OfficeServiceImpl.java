package cn.ddzu.shop.service.impl;

import cn.ddzu.shop.dao.BuildingMapperDao;
import cn.ddzu.shop.dao.OfficeMapperDao;
import cn.ddzu.shop.entity.Building;
import cn.ddzu.shop.entity.Office;
import cn.ddzu.shop.service.OfficeService;
import cn.ddzu.shop.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeMapperDao officeMapperDao;
    @Autowired
    private BuildingMapperDao buildingMapperDao;

    @Override
    public void reset() {
        officeMapperDao.drop();
        officeMapperDao.create();
        buildingMapperDao.drop();
        buildingMapperDao.create();
    }

    @Override
    public void addOffice(Office office) {
        Log.d("start insert office");
        officeMapperDao.insertOffice(office);
    }

    @Override
    public Office getOffice(Long id) {
        if (id == null) return null;
        Log.d("start get office by id : " + id);
        return officeMapperDao.selectOfficeById(id);
    }

    @Override
    public List<Office> getOfficeList(SearchBean searchBean, int page, int step) {
        Log.d("start get office list");
        return officeMapperDao.selectOfficeList(searchBean.getKeyword()
                , searchBean.getBuilding_id()
                , searchBean.getDistrict_id()
                , searchBean.getZone_id()
                , searchBean.getMetro_name()
                , searchBean.getType_id()
                , searchBean.getArea_range_id()
                , searchBean.getPrice_range_id()
                , searchBean.getDecoration_id()
                , page < 0 ? 0 : page * step
                , step);
    }

    @Override
    public int getOfficeSize(SearchBean searchBean) {
        Log.d("start get office size");
        Integer size = officeMapperDao.countOfficeList(searchBean.getKeyword()
                , searchBean.getBuilding_id()
                , searchBean.getDistrict_id()
                , searchBean.getZone_id()
                , searchBean.getMetro_name()
                , searchBean.getType_id()
                , searchBean.getArea_range_id()
                , searchBean.getPrice_range_id()
                , searchBean.getDecoration_id());
        return size == null ? 0 : size;
    }

    @Override
    public void addBuilding(Building building) {
        Log.d("start insert building");
        buildingMapperDao.insertBuilding(building);
    }

    @Override
    public Building getBuilding(Long id) {
        if (id == null) return null;
        Log.d("start get building by id : " + id);
        return buildingMapperDao.selectBuildingById(id);
    }

    @Override
    public List<Building> getBuildingList(SearchBean searchBean, int page, int step) {
        Log.d("start get building list");
        return buildingMapperDao.selectBuildingList(searchBean.getKeyword()
                , searchBean.getDistrict_id()
                , searchBean.getZone_id()
                , searchBean.getMetro_name()
                , searchBean.getType_id()
                , searchBean.getArea_range_id()
                , searchBean.getPrice_range_id()
                , searchBean.getDecoration_id()
                , page < 0 ? 0 : page * step
                , step);
    }

    @Override
    public int getBuildingSize(SearchBean searchBean) {
        Log.d("start get building size");
        Integer size = buildingMapperDao.countBuildingList(searchBean.getKeyword()
                , searchBean.getDistrict_id()
                , searchBean.getZone_id()
                , searchBean.getMetro_name()
                , searchBean.getType_id()
                , searchBean.getArea_range_id()
                , searchBean.getPrice_range_id()
                , searchBean.getDecoration_id());
        return size == null ? 0 : size;
    }
}
