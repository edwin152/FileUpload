package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.NewsTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsTagMapperDao extends Dao<NewsTag> {

    @Deprecated
    void update(NewsTag data);

    /**
     * 查询新闻标签列表
     */
    List<NewsTag> select();
}
