package cn.ddzu.shop.service;

import cn.ddzu.shop.entity.News;

import java.util.List;

public interface NewsService {

    void reset();

    void addNews(News news);

    void deleteNews(Long id);

    void updateNews(News news);

    News getNews(Long id);

    List<News> getNewsList(int page, int step);

    int getNewsSize();
}
