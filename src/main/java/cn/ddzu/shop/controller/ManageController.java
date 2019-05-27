package cn.ddzu.shop.controller;

import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.manager.LoginManager;
import cn.ddzu.shop.service.UserService;
import cn.ddzu.shop.util.Log;
import cn.ddzu.shop.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/manage")
public class ManageController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String manage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("manage", helper);

        String username = helper.getString("username", "");
        String password = helper.getString("password", "");

        String tokenId = LoginManager.getInstance().login(userService, username, password);

        if (tokenId == null) {
            request.removeAttribute("username");
            request.removeAttribute("password");
            return "/login";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("token_id", tokenId);
//            return "redirect:/manage/buildings";
            return "/manage";
        }
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("manage-edit", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (isLogin) {
            return "/edit";
        } else {
            return "redirect:/manage";
        }
    }

    @RequestMapping("/buildings")
    public String buildings(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("manage-buildings", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (isLogin) {
            return "/buildings";
        } else {
            return "redirect:/manage";
        }
    }

    @RequestMapping("/editBuilding")
    public String editBuilding(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("manage-editBuilding", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (isLogin) {
            return "/editBuilding";
        } else {
            return "redirect:/manage";
        }
    }

    @RequestMapping("/editOffice")
    public String editOffice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("manage-editOffice", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (isLogin) {
            return "/editOffice";
        } else {
            return "redirect:/manage";
        }
    }

    @RequestMapping("/offices")
    public String offices(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("manage-offices", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (isLogin) {
            return "/offices";
        } else {
            return "redirect:/manage";
        }
    }

    @RequestMapping("/editNews")
    public String editNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("manage-editOffice", helper);

        boolean isLogin = SessionUtils.checkSession(request.getSession());
        if (isLogin) {
            return "/editNews";
        } else {
            return "redirect:/manage";
        }
    }
}
