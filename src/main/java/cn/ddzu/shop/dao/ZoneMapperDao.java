package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Zone;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneMapperDao {

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
     * 通过区id查区域列表
     *
     * @param district_id 区id
     */
    List<Zone> selectByDistrict(@Param("district_id") long district_id);

    /**
     * 通过id查区域
     *
     * @param id 区域id
     */
    Zone selectById(@Param("id") long id);
}
