package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapperDao {

    /**
     * 查询办公类型列表
     */
    List<Type> selectTypeList();
}
