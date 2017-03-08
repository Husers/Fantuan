package com.suz.fantuan.utils;

import com.google.gson.Gson;
import org.bson.Document;

import java.lang.reflect.Method;

/**
 * Created by Huliang
 * On 17.3.7.
 */
public class DaoUtils {
    public <T> T docmentToBean(Document document,Class<T> tClass){
        String json = document.toJson();
        Gson gson = new Gson();
        return gson.fromJson(json,tClass);
    }

    private String TSet(String s){
        if (!(Character.isUpperCase(s.charAt(0)))){
            s = (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
        return (new StringBuilder("set")).append(s).toString();
    }
}
