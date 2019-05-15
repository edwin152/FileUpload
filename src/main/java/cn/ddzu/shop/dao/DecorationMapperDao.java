package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Decoration;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorationMapperDao {

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
     * 查询装修情况列表
     */
    List<Decoration> select();

    /**
     * 查询装修情况列表
     */
    List<Decoration> selectByBuilding(@Param("building_id") Long building_id);
}
