package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.District;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictMapperDao {

    /**
     * 查询区列表
     */
    List<District> selectDistrictList();
}
