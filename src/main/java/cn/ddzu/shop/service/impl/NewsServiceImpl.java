package cn.ddzu.shop.service.impl;

import cn.ddzu.shop.dao.NewsMapperDao;
import cn.ddzu.shop.entity.News;
import cn.ddzu.shop.service.NewsService;
import cn.ddzu.shop.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapperDao newsMapperDao;

    @Override
    public void reset() {
        newsMapperDao.drop();
        newsMapperDao.create();
    }

    @Override
    public void addNews(News news) {
        Date date = new Date(System.currentTimeMillis());
        news.setId(DataUtils.generateId());
        news.setCreate_time(date);
        news.setLast_edit_time(date);
        newsMapperDao.add(news);
    }

    @Override
    public void deleteNews(Long id) {
        if (id == null) return;
        newsMapperDao.delete(id);
    }

    @Override
    public void updateNews(News news) {
        Date date = new Date(System.currentTimeMillis());
        news.setLast_edit_time(date);
        newsMapperDao.update(news);
    }

    @Override
    public List<News> getNewsList() {
        return newsMapperDao.select();
    }

    @Override
    public News getNews(Long id) {
        return newsMapperDao.selectById(id);
    }
}
