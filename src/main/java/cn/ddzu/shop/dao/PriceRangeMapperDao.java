package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.PriceRange;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRangeMapperDao {

    /**
     * 删除表
     */
    void drop();

    /**
     * 创建表
     */
    void create();

    /**
     * 初始化表
     */
    void init();

    /**
     * 查询价格区间列表
     */
    List<PriceRange> select();

    /**
     * 查询价格区间列表
     */
    List<PriceRange> selectByBuilding(@Param("building_id") Long building_id);
}
