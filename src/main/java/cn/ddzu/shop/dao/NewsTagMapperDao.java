package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.NewsTag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsTagMapperDao extends Dao<NewsTag> {

    /**
     * 查询新闻标签列表
     */
    List<NewsTag> select();
}
