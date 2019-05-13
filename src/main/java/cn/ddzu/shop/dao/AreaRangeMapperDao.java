package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.AreaRange;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRangeMapperDao {

    /**
     * 查询面积区间列表
     */
    List<AreaRange> selectAreaRangeList();

    /**
     * 查询面积区间列表
     */
    List<AreaRange> selectAreaRangeListByBuilding(@Param("building_id") Long building_id);


}
