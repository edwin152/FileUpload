package cn.ddzu.shop.controller;

import cn.ddzu.shop.enums.ResultCode;
import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.service.BasicService;
import cn.ddzu.shop.service.NewsService;
import cn.ddzu.shop.service.OfficeService;
import cn.ddzu.shop.service.UserService;
import cn.ddzu.shop.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/db")
public class DbController extends BaseController {

    @Autowired
    private BasicService basicService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;

    @RequestMapping("/resetUser")
    public void resetUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("db-resetUser", helper);

//        userService.reset();

        finish(response, ResultCode.SUCCESS);
    }

    @RequestMapping("/resetNewsTag")
    public void resetNewsTag(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("db-resetNewsTag", helper);

        newsService.resetNewsTag();
        newsService.resetNews();

        finish(response, ResultCode.SUCCESS);
    }

    @RequestMapping("/resetMetro")
    public void resetMetro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("db-resetMetro", helper);

        basicService.resetMetro();

        finish(response, ResultCode.SUCCESS);
    }
}
