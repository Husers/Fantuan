package com.suz.fantuan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Huliang
 * On 17.3.7.
 */
@Controller
@RequestMapping
public class RouteController {
    @RequestMapping
    public String LoadLoginPage() {
        return "login";
    }

    @RequestMapping("index")
    public String LoadIndexPage() {
        return "index";
    }

    @RequestMapping("refill")
    public String LoadRefillPage() {
        return "refill";
    }

    @RequestMapping("record")
    public String LoadRecordPage() {
        return "record";
    }
}
