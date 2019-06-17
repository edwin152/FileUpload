package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Zone;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneMapperDao extends Dao<Zone> {

    @Override
    void drop();

    @Override
    void create();

    @Override
    void init();

    @Override
    List<Zone> selectAll();

    @Override
    void insert(Zone data);

    @Override
    void update(Zone data);

    /**
     * 通过区id查区域列表
     *
     * @param district_id 区id
     */
    List<Zone> selectByDistrict(@Param("district_id") long district_id);

    /**
     * 获取核心区域
     */
    List<Zone> selectCenterZone();

    /**
     * 通过id查区域
     */
    Zone selectById(@Param("id") long id);
}
