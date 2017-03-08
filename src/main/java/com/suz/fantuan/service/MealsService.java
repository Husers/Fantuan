package com.suz.fantuan.service;

import com.suz.fantuan.model.Record;

import java.util.List;

/**
 * Created by Huliang
 * On 17.3.7.
 */
public interface MealsService {
    List<Record> findRecordByName(String username);
    void addRecord(Record record);
    String refillMeals(String username,String money);
    String spendMeals(String username,String money);
}
