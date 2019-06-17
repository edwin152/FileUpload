package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Decoration;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorationMapperDao extends Dao<Decoration> {

    @Deprecated
    void update(Decoration data);

    /**
     * 查询装修情况列表
     */
    List<Decoration> select();

    /**
     * 查询装修情况列表
     */
    List<Decoration> selectByBuilding(@Param("building_id") Long building_id);
}
