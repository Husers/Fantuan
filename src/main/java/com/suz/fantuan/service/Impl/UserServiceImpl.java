package com.suz.fantuan.service.Impl;

import com.suz.fantuan.dao.UserDao;
import com.suz.fantuan.model.User;
import com.suz.fantuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Huliang
 * On 17.3.6.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public void registerUser(User user) {
        dao.save(user);
    }

    @Override
    public boolean checkPassword(User user) {
        return dao.findPasswordByName(user) == 1;
    }

    @Override
    public void modifyPassword(User user) {
        dao.updatePasswordByName(user);
    }
}
