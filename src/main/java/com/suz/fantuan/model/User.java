package com.suz.fantuan.model;

import org.springframework.stereotype.Component;

/**
 * Created by Huliang
 * On 17.3.6.
 */
@Component
public class User {
    private String username;
    private String password;
    private String meals;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMeals() {
        return meals;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", meals='" + meals + '\'' +
                '}';
    }
}
