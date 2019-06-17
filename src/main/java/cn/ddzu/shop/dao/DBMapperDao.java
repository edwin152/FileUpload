package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBMapperDao extends Dao<Type> {

    void update_1();
}
