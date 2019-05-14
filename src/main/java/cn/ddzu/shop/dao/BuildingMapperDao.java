package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Building;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingMapperDao {

    /**
     * 插入办公室
     *
     * @param building 办公室信息
     */
    void insertBuilding(@Param("building") Building building);

    /**
     * 按id查找楼
     */
    Building selectBuildingById(@Param("id") Long id);

    /**
     * 按条件筛选办公室
     *
     * @param keyword        关键词
     * @param district_id    区id
     * @param zone_id        区域id
     * @param metro_name     地铁名字
     * @param type_id        办公室类型id
     * @param area_range_id  面积区间id
     * @param price_range_id 价格区间id
     * @param decoration_id  装修类型id
     * @param start          开始位置
     * @param step           请求总量
     */
    List<Building> selectBuildingList(@Param("keyword") String keyword
            , @Param("district_id") Long district_id
            , @Param("zone_id") Long zone_id
            , @Param("metro_name") String metro_name
            , @Param("type_id") Long type_id
            , @Param("area_range_id") Long area_range_id
            , @Param("price_range_id") Long price_range_id
            , @Param("decoration_id") Long decoration_id
            , @Param("start") int start
            , @Param("step") int step);

    /**
     * 按条件筛选办公室数量
     *
     * @param keyword        关键词
     * @param district_id    区id
     * @param zone_id        区域id
     * @param metro_name     地铁名字
     * @param type_id        办公室类型id
     * @param area_range_id  面积区间id
     * @param price_range_id 价格区间id
     * @param decoration_id  装修类型id
     */
    Integer countBuildingList(@Param("keyword") String keyword
            , @Param("district_id") Long district_id
            , @Param("zone_id") Long zone_id
            , @Param("metro_name") String metro_name
            , @Param("type_id") Long type_id
            , @Param("area_range_id") Long area_range_id
            , @Param("price_range_id") Long price_range_id
            , @Param("decoration_id") Long decoration_id);
}
