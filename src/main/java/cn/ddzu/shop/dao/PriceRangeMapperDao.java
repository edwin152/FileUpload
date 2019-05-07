package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.PriceRange;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRangeMapperDao {

    // 查询价格区间列表
    List<PriceRange> selectPriceRangeList();
}
