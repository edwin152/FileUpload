package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapperDao extends Dao<User> {

    @Deprecated
    void update(User data);

    /**
     * 查询办公类型列表
     */
    User select(@Param("username") String username);
}
