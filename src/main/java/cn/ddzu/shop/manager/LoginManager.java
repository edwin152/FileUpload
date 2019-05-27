package cn.ddzu.shop.manager;

import cn.ddzu.shop.entity.User;
import cn.ddzu.shop.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginManager {

    private static LoginManager instance;

    private final Map<String, User> userMap;

    public synchronized static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    private LoginManager() {
        userMap = new HashMap<>();
    }

    /**
     * @return tokenId 验证登录的id
     */
    public String login(UserService userService, String username, String password) {
        if (username == null || password == null) {
            return null;
        }

        User user = userService.getUser(username);
        if (user == null || !password.equals(user.getPassword())) {
            return null;
        }

        String tokenId = UUID.randomUUID().toString();
        user.setTokenId(tokenId);
        userMap.put(username, user);
        return tokenId;
    }

    /**
     * 检测登录状态
     */
    public boolean check(String username, String tokenId) {
        if (username == null || tokenId == null) {
            return false;
        }

        User user = userMap.get(username);
        return user != null && tokenId.equals(user.getTokenId());
    }
}
