package cn.ddzu.shop.service.impl;

import cn.ddzu.shop.dao.*;
import cn.ddzu.shop.entity.Note;
import cn.ddzu.shop.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBServiceImpl implements DBService {

    private static final String KEY_DB_VERSION_CODE = "db_version_code";

    @Autowired
    private DBMapperDao dbMapperDao;

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
    @Autowired
    private BuildingMapperDao buildingMapperDao;
    @Autowired
    private NewsMapperDao newsMapperDao;
    @Autowired
    private NewsTagMapperDao newsTagMapperDao;
    @Autowired
    private UserMapperDao userMapperDao;
    @Autowired
    private NoteMapperDao noteMapperDao;

    @Override
    public void updateDB() {
        int dbVersionCode = -1;
        try {
            Note db_version_code = noteMapperDao.selectByKey(KEY_DB_VERSION_CODE);
            if (db_version_code != null) {
                dbVersionCode = Integer.parseInt(db_version_code.getV());
            }
        } catch (RuntimeException ignore) {
            ignore.printStackTrace();
        }

        switch (dbVersionCode) {
            case -1:
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

                newsTagMapperDao.drop();
                newsTagMapperDao.create();
                newsTagMapperDao.init();

                noteMapperDao.drop();
                noteMapperDao.create();
                noteMapperDao.init();

                userMapperDao.drop();
                userMapperDao.create();
                userMapperDao.init();

                officeMapperDao.drop();
                officeMapperDao.create();

                buildingMapperDao.drop();
                buildingMapperDao.create();

                newsMapperDao.drop();
                newsMapperDao.create();
            case 0:
//                dbMapperDao.update_0();
            case 1:
                dbVersionCode = 1;
        }

        Note note = new Note();
        note.setK(KEY_DB_VERSION_CODE);
        note.setV(String.valueOf(dbVersionCode));
        noteMapperDao.update(note);
    }
}
