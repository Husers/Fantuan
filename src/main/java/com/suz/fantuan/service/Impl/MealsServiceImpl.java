package com.suz.fantuan.service.Impl;

import com.suz.fantuan.dao.MealsDao;
import com.suz.fantuan.dao.RecordDao;
import com.suz.fantuan.model.Record;
import com.suz.fantuan.service.MealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Huliang
 * On 17.3.7.
 */
@Service
public class MealsServiceImpl implements MealsService {
    private final MealsDao mealsDao;
    private final RecordDao recordDao;

    @Autowired
    public MealsServiceImpl(MealsDao mealsDao, RecordDao recordDao) {
        this.mealsDao = mealsDao;
        this.recordDao = recordDao;
    }

    @Override
    public List<Record> findRecordByName(String username) {
        return recordDao.findRecordByName(username);
    }

    @Override
    public void addRecord(Record record) {
        recordDao.save(record);
    }

    @Override
    public String refillMeals(String username, String money) {
        BigDecimal balance = new BigDecimal(mealsDao.findMealsByName(username));
        BigDecimal moneyNum = new BigDecimal(money);
        money = balance.add(moneyNum).toString();
        mealsDao.updateMealsByName(username,money);
        return money;
    }

    @Override
    public String spendMeals(String username, String money) {
        BigDecimal balance = new BigDecimal(mealsDao.findMealsByName(username));
        BigDecimal moneyNum = new BigDecimal(money);
        money = balance.subtract(moneyNum).toString();
        mealsDao.updateMealsByName(username,money);
        return money;
    }
}
