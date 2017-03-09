package com.suz.fantuan.web;

import com.suz.fantuan.model.Record;
import com.suz.fantuan.properties.Web;
import com.suz.fantuan.service.MealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    public ModelAndView Refill(@CookieValue(Web.BS_COOKIE_ACCOUNT) String username, String money) {
        String nowMoney = service.refillMeals(username, money);
        record.setUsername(username);
        record.setMoney(money);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setType("充值");
        service.addRecord(record);
        return new ModelAndView("refill", "nowMoney", nowMoney);
    }

    @RequestMapping("spend.action")
    @ResponseBody
    public ModelAndView Spend(@CookieValue(Web.BS_COOKIE_ACCOUNT) String username, String money) {
        String nowMoney = service.spendMeals(username, money);
        record.setUsername(username);
        record.setMoney(money);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setType("消费");
        service.addRecord(record);
        return new ModelAndView("index", "nowMoney", nowMoney);
    }

    @RequestMapping("record.action")
    public ModelAndView Record(@CookieValue(Web.BS_COOKIE_ACCOUNT) String username) {
        ModelMap model = new ModelMap();
        model.addAttribute("recordList", service.findRecordByName(username));
        model.addAttribute("sumMeals", service.findSumMeals());
        return new ModelAndView("record", model);
    }
}
