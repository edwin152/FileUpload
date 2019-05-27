package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsMapperDao {

    /**
     * 删除表
     */
    void drop();

    /**
     * 创建表
     */
    void create();

    /**
     * 添加咨询
     */
    void add(@Param("news") News news);

    /**
     * 更新咨询
     */
    void update(@Param("news") News news);

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

    List<News> selectWhereHot();
}
