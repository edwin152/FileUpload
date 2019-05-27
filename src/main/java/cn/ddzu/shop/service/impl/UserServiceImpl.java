package cn.ddzu.shop.service.impl;

import cn.ddzu.shop.dao.UserMapperDao;
import cn.ddzu.shop.entity.User;
import cn.ddzu.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapperDao userMapperDao;

    @Override
    public void reset() {
        userMapperDao.drop();
        userMapperDao.create();
    }

    @Override
    public User getUser(String username) {
        if (username == null) return null;
        return userMapperDao.select(username);
    }
}
