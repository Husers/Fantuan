package com.suz.fantuan.web;

import com.suz.fantuan.properties.Web;
import com.suz.fantuan.model.Record;
import com.suz.fantuan.service.MealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Huliang
 * On 17.3.7.
 */
@Controller
@RequestMapping("/")
public class MealsController {
    private final MealsService service;
    private final Record record;

    @Autowired
    public MealsController(MealsService service, Record record) {
        this.service = service;
        this.record = record;
    }

    @RequestMapping("refill.action")
    @ResponseBody
    public String Refill(@CookieValue(Web.BS_COOKIE_ACCOUNT) String username, String money) {
        String nowMoney = service.refillMeals(username, money);
        record.setUsername(username);
        record.setMoney(money);
        service.addRecord(record);
        return nowMoney;
    }

    @RequestMapping("spend.action")
    @ResponseBody
    public String Spend(@CookieValue(Web.BS_COOKIE_ACCOUNT) String username, String money) {
        String nowMoney = service.spendMeals(username, money);
        record.setUsername(username);
        record.setMoney(money);
        service.addRecord(record);
        return nowMoney;
    }

    @RequestMapping("record.action")
    public List<Record> Record(@CookieValue(Web.BS_COOKIE_ACCOUNT) String username) {
        return service.findRecordByName(username);
    }
}
