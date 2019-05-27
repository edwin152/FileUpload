package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapperDao {

    /**
     * 删除表
     */
    void drop();

    /**
     * 创建表
     */
    void create();

    /**
     * 查询办公类型列表
     */
    User select(@Param("username") String username);
}
