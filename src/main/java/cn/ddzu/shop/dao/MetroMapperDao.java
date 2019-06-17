package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Metro;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetroMapperDao extends Dao<Metro> {

    @Deprecated
    void update(Metro data);

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
