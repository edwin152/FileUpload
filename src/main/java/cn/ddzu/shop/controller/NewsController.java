package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.News;
import cn.ddzu.shop.enums.ResultCode;
import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.service.NewsService;
import cn.ddzu.shop.util.JsonUtils;
import cn.ddzu.shop.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    /**
     * 查询热门新闻
     */
    @RequestMapping("/hot")
    public void newsTag(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("filter-newsTag", helper);

        List<News> newsList = newsService.getHotNews();

        finish(response, ResultCode.SUCCESS, new Gson().toJsonTree(newsList));
    }

    /**
     * 查询咨询
     * <p>
     * id 咨询id
     */
    @RequestMapping("/news")
    public void getNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("search-news", helper);

        Long id = helper.getLong("id");

        News news = newsService.getNews(id);
        JsonObject newsObject = new Gson().toJsonTree(news).getAsJsonObject();

        finish(response, ResultCode.SUCCESS, newsObject);
    }

    /**
     * 搜索咨询
     * <p>
     * news_tag_id 新闻标签
     * page 页码
     */
    @RequestMapping("/newsList")
    public void getNewsList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-newsList", helper);

        Long news_tag_id = helper.getLong("news_tag_id", 1L);
        Integer page = helper.getInt("page", 0);

        List<News> newsList = newsService.getNewsList(news_tag_id, page, PAGE_SIZE);
        JsonArray newsArray = new JsonArray();
        for (News news : newsList) {
            String content = news.getContent();
            if (content != null) {
                content = content.replaceAll("</?.+?/?>", "");
                if (content.length() > 200) {
                    content = content.substring(0, 200);
                }
                news.setContent(content);
            }
            JsonObject newsObject = new Gson().toJsonTree(news).getAsJsonObject();
            newsObject.add("img_list", JsonUtils.strToStringArray(news.getImg_list()));
            newsArray.add(newsObject);
        }

        int size = newsService.getNewsSize(news_tag_id);
        int pageNum = size == 0 ? 0 : (size - 1) / PAGE_SIZE + 1;

        JsonObject json = new JsonObject();
        json.add("newsList", newsArray);
        json.addProperty("pageNum", pageNum);
        json.addProperty("pageIndex", page);

        finish(response, ResultCode.SUCCESS, json);
    }
}