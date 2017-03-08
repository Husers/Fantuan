package com.suz.fantuan.service;

import com.suz.fantuan.model.User;

/**
 * Created by Huliang
 * On 17.3.6.
 */
public interface UserService{
    void registerUser(User user);
    boolean checkPassword(User user);
    void modifyPassword(User user);
}
