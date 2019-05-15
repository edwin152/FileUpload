package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Metro;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetroMapperDao {

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
     * 查询地铁列表
     */
    List<Metro> select();

    /**
     * 查询地铁
     *
     * @param id 地铁id
     */
    Metro selectById(@Param("id") Long id);
}
