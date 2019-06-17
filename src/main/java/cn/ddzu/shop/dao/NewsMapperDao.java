package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsMapperDao extends Dao<News> {

    @Override
    void drop();

    @Override
    void create();

    @Deprecated
    void init();

    @Override
    List<News> selectAll();

    @Override
    void insert(News data);

    @Override
    void update(News data);

    /**
     * 删除咨询
     */
    void delete(@Param("id") Long id);

    /**
     * 查询咨询
     */
    List<News> select(@Param("news_tag_id") Long news_tag_id
            , @Param("start") int start
            , @Param("step") int step);

    /**
     * 查询咨询
     */
    News selectById(@Param("id") Long id);

    /**
     * 查询咨询数量
     */
    Integer count(@Param("news_tag_id") Long news_tag_id);

    /**
     * 查询热门
     */
    List<News> selectWhereHot(@Param("limit") int limit);

    /**
     * 查询最新添加
     */
    List<News> selectWhereNew(@Param("limit") int limit);
}
