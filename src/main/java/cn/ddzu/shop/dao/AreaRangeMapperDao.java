package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.AreaRange;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRangeMapperDao {

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
     * 查询面积区间列表
     */
    List<AreaRange> select();

    /**
     * 查询面积区间列表
     */
    List<AreaRange> selectByBuilding(@Param("building_id") Long building_id);


}
