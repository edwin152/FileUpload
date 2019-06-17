package cn.ddzu.shop.service;

import cn.ddzu.shop.entity.*;

import java.util.List;

public interface FilterService {

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
     * 获取核心商圈
     */
    List<Zone> getCoreZoneList();

    /**
     * 更新区域
     */
    void updateZone(Zone zone);

    /**
     * 获取区域
     */
    Zone getZone(Long id);

    /**
     * 获取地铁列表
     */
    List<Metro> getMetroList();

    /**
     * 获取地铁
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
     * 通过面积获取面积区间id
     */
    Long getAreaRangeId(Float area);

    /**
     * 查询价格区间列表
     */
    List<PriceRange> getPriceRangeList();

    /**
     * 查询价格区间列表
     */
    List<PriceRange> getPriceRangeList(Long building_id);

    /**
     * 通过价格获取价格区间id
     */
    Long getPriceRangeId(Float price);

    /**
     * 查询装修情况列表
     */
    List<Decoration> getDecorationList();

    /**
     * 查询装修情况列表
     */
    List<Decoration> getDecorationList(Long building_id);
}
