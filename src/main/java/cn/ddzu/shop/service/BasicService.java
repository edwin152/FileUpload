package cn.ddzu.shop.service;

import cn.ddzu.shop.entity.*;

import java.util.List;

public interface BasicService {

    /**
     * 重置数据库
     */
    void reset();

    /**
     * 获取区列表
     */
    List<District> getDistrictList();

    /**
     * 获取地区列表
     *
     * @param district_id 区id
     */
    List<Zone> getZoneList(Long district_id);

    /**
     * 获取区域
     *
     * @param id 区域id
     */
    Zone getZone(Long id);

    /**
     * 获取地铁列表
     */
    List<Metro> getMetroList();

    /**
     * 获取地铁
     *
     * @param id 地铁id
     */
    Metro getMetro(Long id);

    /**
     * 获取办公类型列表
     */
    List<Type> getTypeList();

    /**
     * 获取办公类型列表
     */
    List<Type> getTypeList(Long building_id);

    /**
     * 查询面积区间列表
     */
    List<AreaRange> getAreaRangeList();

    /**
     * 查询面积区间列表
     */
    List<AreaRange> getAreaRangeList(Long building_id);

    /**
     * 查询价格区间列表
     */
    List<PriceRange> getPriceRangeList();

    /**
     * 查询价格区间列表
     */
    List<PriceRange> getPriceRangeList(Long building_id);

    /**
     * 查询装修情况列表
     */
    List<Decoration> getDecorationList();

    /**
     * 查询装修情况列表
     */
    List<Decoration> getDecorationList(Long building_id);
}
