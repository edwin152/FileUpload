package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.PriceRange;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRangeMapperDao extends Dao<PriceRange> {

    @Override
    void drop();

    @Override
    void create();

    @Override
    void init();

    @Override
    List<PriceRange> selectAll();

    @Override
    void insert(PriceRange data);

    @Deprecated
    void update(PriceRange data);

    /**
     * 查询价格区间列表
     */
    List<PriceRange> select();

    /**
     * 查询价格区间列表
     */
    List<PriceRange> selectByBuilding(@Param("building_id") Long building_id);
}
