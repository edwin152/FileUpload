package cn.ddzu.shop.service.impl;

import cn.ddzu.shop.dao.*;
import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        map.put("note", noteMapperDao.selectAll());
        return map;
    }

    @Override
    public void setAll(Map<String, List> data) {
        List areaRangeList = data.get("area_range");
        areaRangeMapperDao.drop();
        areaRangeMapperDao.create();
        if (areaRangeList != null) {
            for (Object areaRange : areaRangeList) {
                if (!(areaRange instanceof AreaRange)) continue;
                areaRangeMapperDao.insert((AreaRange) areaRange);
            }
        }

        List buildingList = data.get("building");
        buildingMapperDao.drop();
        buildingMapperDao.create();
        if (buildingList != null) {
            for (Object building : buildingList) {
                if (!(building instanceof Building)) continue;
                buildingMapperDao.insert((Building) building);
            }
        }

        List districtList = data.get("district");
        districtMapperDao.drop();
        districtMapperDao.create();
        if (districtList != null) {
            for (Object district : districtList) {
                if (!(district instanceof District)) continue;
                districtMapperDao.insert((District) district);
            }
        }

        List decorationList = data.get("decoration");
        decorationMapperDao.drop();
        decorationMapperDao.create();
        if (decorationList != null) {
            for (Object decoration : decorationList) {
                if (!(decoration instanceof Decoration)) continue;
                decorationMapperDao.insert((Decoration) decoration);
            }
        }

        List metroList = data.get("metro");
        metroMapperDao.drop();
        metroMapperDao.create();
        if (metroList != null) {
            for (Object metro : metroList) {
                if (!(metro instanceof Metro)) continue;
                metroMapperDao.insert((Metro) metro);
            }
        }

        List newsList = data.get("news");
        newsMapperDao.drop();
        newsMapperDao.create();
        if (newsList != null) {
            for (Object news : newsList) {
                if (!(news instanceof News)) continue;
                newsMapperDao.insert((News) news);
            }
        }

        List newsTagList = data.get("news_tag");
        newsTagMapperDao.drop();
        newsTagMapperDao.create();
        if (newsTagList != null) {
            for (Object newsTag : newsTagList) {
                if (!(newsTag instanceof NewsTag)) continue;
                newsTagMapperDao.insert((NewsTag) newsTag);
            }
        }

        List officeList = data.get("office");
        officeMapperDao.drop();
        officeMapperDao.create();
        if (officeList != null) {
            for (Object office : officeList) {
                if (!(office instanceof Office)) continue;
                officeMapperDao.insert((Office) office);
            }
        }

        List priceRangeList = data.get("price_range");
        priceRangeMapperDao.drop();
        priceRangeMapperDao.create();
        if (priceRangeList != null) {
            for (Object priceRange : priceRangeList) {
                if (!(priceRange instanceof PriceRange)) continue;
                priceRangeMapperDao.insert((PriceRange) priceRange);
            }
        }

        List typeList = data.get("type");
        typeMapperDao.drop();
        typeMapperDao.create();
        if (typeList != null) {
            for (Object type : typeList) {
                if (!(type instanceof Type)) continue;
                typeMapperDao.insert((Type) type);
            }
        }

        List userList = data.get("user");
        userMapperDao.drop();
        userMapperDao.create();
        if (userList != null) {
            for (Object user : userList) {
                if (!(user instanceof User)) continue;
                userMapperDao.insert((User) user);
            }
        }

        List zoneList = data.get("zone");
        zoneMapperDao.drop();
        zoneMapperDao.create();
        if (zoneList != null) {
            for (Object zone : zoneList) {
                if (!(zone instanceof Zone)) continue;
                zoneMapperDao.insert((Zone) zone);
            }
        }

        List noteList = data.get("note");
        noteMapperDao.drop();
        noteMapperDao.create();
        if (noteList != null) {
            for (Object note : noteList) {
                if (!(note instanceof Note)) continue;
                noteMapperDao.insert((Note) note);
            }
        }
    }
}
