package cn.ddzu.shop.service;

import cn.ddzu.shop.entity.News;
import cn.ddzu.shop.entity.NewsTag;

import java.util.List;

public interface NewsService {

    List<NewsTag> getNewsTagList();

    void addNews(News news);

    void deleteNews(Long id);

    void updateNews(News news);

    News getNews(Long id);

    List<News> getNewsList(Long news_tag_id, int page, int step);

    int getNewsSize(Long news_tag_id);

    List<News> getHotNews();

    List<News> getIndexNews();
}
