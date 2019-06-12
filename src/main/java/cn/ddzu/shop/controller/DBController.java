package cn.ddzu.shop.controller;

import cn.ddzu.shop.enums.ResultCode;
import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.service.*;
import cn.ddzu.shop.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/db")
public class DBController extends BaseController {

    @Autowired
    private DBService dbService;

    @RequestMapping("/update")
    public void resetUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("db-resetUser", helper);

        dbService.updateDB();

        finish(response, ResultCode.SUCCESS);
    }
}
