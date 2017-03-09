package com.suz.fantuan.web;

import com.suz.fantuan.properties.Web;
import com.suz.fantuan.model.User;
import com.suz.fantuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Huliang
 * On 17.3.6.
 */
@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final User user;

    @Autowired
    public UserController(UserService userService, User user) {
        this.userService = userService;
        this.user = user;
    }

    @RequestMapping("login.action")
    public String Login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("pass") != null && request.getParameter("user") != null) {
            user.setUsername(request.getParameter("user"));
            user.setPassword(request.getParameter("pass"));
            if (userService.checkPassword(user)) {
                Cookie accountCookie = new Cookie(Web.BS_COOKIE_ACCOUNT, request.getParameter("user"));
                accountCookie.setMaxAge(Web.BS_COOKIE_MAX_AGE);
                accountCookie.setPath(Web.LOGIN_URL);
                response.addCookie(accountCookie);
                if (null != request.getParameter("auto")) {
                    if (request.getParameter("auto").equals("1")) {
                        Cookie autoCookie = new Cookie(Web.BS_COOKIE_AUTOLOGIN, "1");
                        autoCookie.setMaxAge(Web.BS_COOKIE_MAX_AGE);
                        autoCookie.setPath(Web.LOGIN_URL);
                        response.addCookie(autoCookie);
                        Cookie passwordCookie = new Cookie(Web.BS_COOKIE_PASSWORD, request.getParameter("pass"));
                        passwordCookie.setMaxAge(Web.BS_COOKIE_MAX_AGE);
                        passwordCookie.setPath(Web.LOGIN_URL);
                        response.addCookie(passwordCookie);
                    } else {
                        removeLoginCookie(request, response);
                    }
                }
                return "index";
            }
        }
        return "login";
    }

    @RequestMapping("register.action")
    public String Register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        user.setUsername(request.getParameter("user"));
        user.setPassword(request.getParameter("pass"));
        userService.registerUser(user);
        Cookie accountCookie = new Cookie(Web.BS_COOKIE_ACCOUNT, request.getParameter("user"));
        accountCookie.setMaxAge(Web.BS_COOKIE_MAX_AGE);
        accountCookie.setPath(Web.LOGIN_URL);
        response.addCookie(accountCookie);
        return "login";
    }

    @RequestMapping("modifyPW.action")
    public String ModifyPW(HttpServletRequest request, HttpServletResponse response, @CookieValue(Web.BS_COOKIE_ACCOUNT) String username, String password) throws IOException {
        user.setUsername(username);
        user.setPassword(password);
        userService.modifyPassword(user);
        removeLoginCookie(request, response);
        return "login";
    }

    private void removeLoginCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case Web.BS_COOKIE_AUTOLOGIN:
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        break;
                    case Web.BS_COOKIE_PASSWORD:
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        break;
                }
            }
        }
    }
}
