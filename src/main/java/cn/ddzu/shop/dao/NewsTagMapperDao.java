package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.NewsTag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsTagMapperDao {

    /**
     * 删除表
     */
    void drop();

    /**
     * 创建表
     */
    void create();

    /**
     * 初始化表
     */
    void init();

    /**
     * 查询新闻标签列表
     */
    List<NewsTag> select();
}
