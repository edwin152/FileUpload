package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Decoration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorationMapperDao {

    /**
     * 查询装修情况列表
     */
    List<Decoration> selectDecorationList();
}
