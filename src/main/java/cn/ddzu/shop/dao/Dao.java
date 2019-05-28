package cn.ddzu.shop.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Dao<T> {

    void drop();

    void create();

    void init();

    List<T> selectAll();

    void insert(@Param("data") T data);

    void update(@Param("data") T data);
}
