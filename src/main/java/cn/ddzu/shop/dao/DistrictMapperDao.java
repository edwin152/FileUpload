package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.District;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictMapperDao extends Dao<District> {

    @Deprecated
    void update(District data);

    /**
     * 查询区列表
     */
    List<District> select();
}
