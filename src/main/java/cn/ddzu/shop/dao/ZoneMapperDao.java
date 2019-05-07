package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Zone;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneMapperDao {

    /**
     * 通过区id查区域列表
     *
     * @param district_id 区id
     */
    List<Zone> selectZoneList(@Param("district_id") long district_id);
}
