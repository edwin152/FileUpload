package cn.ddzu.shop.service;

import cn.ddzu.shop.entity.User;

public interface UserService {

    void reset();

    User getUser(String username);
}
