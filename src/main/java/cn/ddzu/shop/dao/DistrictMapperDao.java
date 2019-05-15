package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.District;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictMapperDao {

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
     * 查询区列表
     */
    List<District> select();
}
