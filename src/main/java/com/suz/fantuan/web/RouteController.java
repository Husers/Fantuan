package com.suz.fantuan.web;

import com.suz.fantuan.properties.Web;
import com.suz.fantuan.service.MealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Huliang
 * On 17.3.7.
 */
@Controller
@RequestMapping("/")
public class RouteController {
    private final MealsService mealsService;

    @Autowired
    public RouteController(MealsService mealsService) {
        this.mealsService = mealsService;
    }

    @RequestMapping
    public String LoadLoginPage() {
        return "login";
    }

    @RequestMapping("index")
    public ModelAndView LoadIndexPage(@CookieValue(Web.BS_COOKIE_ACCOUNT) String username) {
        return new ModelAndView("index", "nowMoney", mealsService.findMealsByName(username));
    }

    @RequestMapping("refill")
    public ModelAndView LoadRefillPage(@CookieValue(Web.BS_COOKIE_ACCOUNT) String username) {
        return new ModelAndView("refill", "nowMoney", mealsService.findMealsByName(username));
    }

    @RequestMapping("record")
    public ModelAndView LoadRecordPage(@CookieValue(Web.BS_COOKIE_ACCOUNT) String username) {
        ModelMap model = new ModelMap();
        model.addAttribute("recordList", mealsService.findRecordByName(username));
        model.addAttribute("sumMeals", mealsService.findSumMeals());
        return new ModelAndView("record", model);
    }
}
