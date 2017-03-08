package com.suz.fantuan.dao;

import com.suz.fantuan.model.User;

/**
 * Created by Huliang
 * On 17.3.6.
 */
public interface UserDao{
    int findPasswordByName(User user);
    void updatePasswordByName(User user);
    void save(User user);
}
