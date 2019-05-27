package cn.ddzu.shop.manager;

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
        User[] user_list = {new User("ddzu", "Ddzuqqq7")};

        userMap = new HashMap<>();
        for (User user : user_list) {
            userMap.put(user.username, user);
        }
    }

    /**
     * @return tokenId 验证登录的id
     */
    public String login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }

        User user = userMap.get(username);
        if (user != null && password.equals(user.password)) {
            String tokenId = UUID.randomUUID().toString();
            user.tokenId = tokenId;
            return tokenId;
        }

        return null;
    }

    /**
     * 检测登录状态
     */
    public boolean check(String username, String tokenId) {
        if (username == null || tokenId == null) {
            return false;
        }

        User user = userMap.get(username);
        return user != null && tokenId.equals(user.tokenId);
    }

    private static class User {
        private String username;
        private String password;
        private String tokenId;

        private User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
