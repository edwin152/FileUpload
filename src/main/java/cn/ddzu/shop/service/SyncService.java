package cn.ddzu.shop.service;

import cn.ddzu.shop.entity.*;

import java.util.List;
import java.util.Map;

public interface SyncService {

    Map<String, List> getAll();

    void setAll(List<AreaRange> areaRangeList,
                List<Building> buildingList,
                List<Decoration> decorationList,
                List<District> districtList,
                List<Metro> metroList,
                List<News> newsList,
                List<NewsTag> newsTagList,
                List<Office> officeList,
                List<PriceRange> priceRangeList,
                List<Type> typeList,
                List<User> userList,
                List<Zone> zoneList);
}
