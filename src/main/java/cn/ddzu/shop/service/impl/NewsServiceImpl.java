package cn.ddzu.shop.service.impl;

import cn.ddzu.shop.dao.NewsMapperDao;
import cn.ddzu.shop.dao.NewsTagMapperDao;
import cn.ddzu.shop.entity.News;
import cn.ddzu.shop.entity.NewsTag;
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
    @Autowired
    private NewsTagMapperDao newsTagMapperDao;

    @Override
    public void resetNewsTag() {
        newsTagMapperDao.drop();
        newsTagMapperDao.create();
        newsTagMapperDao.init();
    }

    @Override
    public List<NewsTag> getNewsTagList() {
        return newsTagMapperDao.select();
    }

    @Override
    public void resetNews() {
        newsMapperDao.drop();
        newsMapperDao.create();
    }

    @Override
    public void addNews(News news) {
        Date date = new Date(System.currentTimeMillis());
        news.setId(DataUtils.generateId());
        news.setCreate_time(date);
        news.setLast_edit_time(date);
        newsMapperDao.insert(news);
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
    public News getNews(Long id) {
        return newsMapperDao.selectById(id);
    }

    @Override
    public List<News> getNewsList(Long news_tag_id, int page, int step) {
        return newsMapperDao.select(news_tag_id
                , page < 0 ? 0 : page * step
                , step);
    }

    @Override
    public int getNewsSize(Long news_tag_id) {
        Integer size = newsMapperDao.count(news_tag_id);
        return size == null ? 0 : size;
    }

    @Override
    public List<News> getHotNews() {
        return newsMapperDao.selectWhereHot();
    }

    @Override
    public List<News> getIndexNews() {
        return null;
    }
}
