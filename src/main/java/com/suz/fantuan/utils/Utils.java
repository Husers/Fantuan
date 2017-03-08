package com.suz.fantuan.utils;

import org.springframework.stereotype.Component;

/**
 * Created by Huliang
 * On 17.3.7.
 */
@Component
public class Utils {
    public String TGet(String s) {
        if (!(Character.isUpperCase(s.charAt(0)))) {
            s = (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
        return (new StringBuilder("get")).append(s).toString();
    }
}
