package cn.ddzu.shop.service;

import cn.ddzu.shop.entity.News;

import java.util.List;

public interface NewsService {

    void reset();

    void addNews(News news);

    void deleteNews(Long id);

    void updateNews(News news);

    List<News> getNewsList();

    News getNews(Long id);
}
