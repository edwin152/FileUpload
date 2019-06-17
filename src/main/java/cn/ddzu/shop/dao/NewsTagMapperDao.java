package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.NewsTag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsTagMapperDao extends Dao<NewsTag> {

    @Override
    void drop();

    @Override
    void create();

    @Override
    void init();

    @Override
    List<NewsTag> selectAll();

    @Override
    void insert(NewsTag data);

    @Deprecated
    void update(NewsTag data);

    /**
     * 查询新闻标签列表
     */
    List<NewsTag> select();
}
