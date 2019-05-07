package cn.ddzu.shop.service;

import cn.ddzu.shop.entity.*;

import java.util.List;

public interface BasicService {

    // 获取区列表
    List<District> getDistrictList();

    // 获取地区列表
    List<Zone> getZoneList(Long district_id);

    // 获取地铁列表
    List<Metro> getMetroList();

    // 获取地铁
    Metro getMetro(Long id);

    // 获取办公类型列表
    List<Type> getTypeList();

    // 查询面积区间列表
    List<AreaRange> getAreaRangeList();

    // 查询价格区间列表
    List<PriceRange> getPriceRangeList();

    // 查询装修情况列表
    List<Decoration> getDecorationList();

    void addOffice(Office office);

    // 查询办公室列表
    List<Office> getOfficeList(Long id
            , String keyword
            , Long business_center_id
            , Long zone_id
            , String metro_name
            , Long type_id
            , Long area_range_id
            , Long price_range_id
            , Long decoration_id);
}
