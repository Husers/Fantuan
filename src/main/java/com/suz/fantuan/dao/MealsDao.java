package com.suz.fantuan.dao;

/**
 * Created by Huliang
 * On 17.3.7.
 */
public interface MealsDao {
    String findMealsByName(String username);

    void updateMealsByName(String username, String meals);
}
