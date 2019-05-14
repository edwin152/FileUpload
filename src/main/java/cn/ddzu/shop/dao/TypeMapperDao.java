package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapperDao {

    /**
     * 查询办公类型列表
     */
    List<Type> selectTypeList();

    /**
     * 查询办公类型列表
     */
    List<Type> selectTypeListByBuilding(@Param("building_id") Long building_id);
}
