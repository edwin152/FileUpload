package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.District;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictMapperDao extends Dao<District> {

    @Override
    void drop();

    @Override
    void create();

    @Override
    void init();

    @Override
    List<District> selectAll();

    @Override
    void insert(District data);

    @Deprecated
    void update(District data);

    /**
     * 查询区列表
     */
    List<District> select();
}
