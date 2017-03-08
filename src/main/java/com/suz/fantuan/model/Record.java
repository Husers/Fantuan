package com.suz.fantuan.model;

import org.springframework.stereotype.Component;

/**
 * Created by Huliang
 * On 17.3.7.
 */
@Component
public class Record {
    private String username;
    private String date;
    private String type;
    private String money;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
