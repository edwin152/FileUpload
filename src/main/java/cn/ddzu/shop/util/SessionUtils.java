package cn.ddzu.shop.util;

import cn.ddzu.shop.manager.LoginManager;

import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static boolean checkSession(HttpSession session) {
        Object username = session.getAttribute("username");
        Object tokenId = session.getAttribute("token_id");
        try {
            return LoginManager.getInstance().check((String) username, (String) tokenId);
        } catch (ClassCastException ignore) {
            return false;
        }
    }
}
