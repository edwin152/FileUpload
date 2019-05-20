package cn.ddzu.shop.controller;

import cn.ddzu.shop.enums.ResultCode;
import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.util.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController extends BaseController {

    private static final String[][] user_map = {
            {"ddzu", "Ddzu748521"}
    };
    private static final Map<String, String> USER_MAP = initUserMap();

    private static Map<String, String> initUserMap() {
        Map<String, String> userMap = new HashMap<>();

        for (String[] user : user_map) {
            String key = user[0];
            String pwd = user[1];
            userMap.put(key, pwd);
        }

        return userMap;
    }

    /**
     * 登录
     * <p>
     * username 用户名
     * password 密码
     */
    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("login", helper);

        String username = helper.getString("username", "");
        String password = helper.getString("password", "");

        String pwd = USER_MAP.get(username);

        ResultCode resultCode;
        if (pwd == null) {
            resultCode = ResultCode.ERROR_NO_USER;
        } else if (!password.equals(pwd)) {
            resultCode = ResultCode.ERROR_WRONG_PASSWORD;
        } else {
            resultCode = ResultCode.SUCCESS;
        }
        finish(response, resultCode);
    }

    @RequestMapping("/manage")
    public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("login", helper);
        return new ModelAndView("login");
    }
}