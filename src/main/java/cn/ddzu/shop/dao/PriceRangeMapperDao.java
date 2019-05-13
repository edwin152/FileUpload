package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.PriceRange;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRangeMapperDao {

    /**
     * 查询价格区间列表
     */
    List<PriceRange> selectPriceRangeList();

    /**
     * 查询价格区间列表
     */
    List<PriceRange> selectPriceRangeListByBuilding(@Param("building_id") Long building_id);
}
