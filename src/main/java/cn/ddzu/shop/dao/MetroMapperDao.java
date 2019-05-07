package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Metro;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetroMapperDao {

    /**
     * 查询地铁列表
     */
    List<Metro> selectMetroList();

    /**
     * 查询地铁
     *
     * @param id 地铁id
     */
    Metro selectMetroById(@Param("id") Long id);
}
