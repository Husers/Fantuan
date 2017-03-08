package com.suz.fantuan.aop;

import com.suz.fantuan.properties.Web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


/**
 * Created by Huliang
 * On 17.3.6.
 */
@WebFilter(urlPatterns = "/**")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HashMap<String, String> map = InitCookie(request);
        if (null != map) {
            String account = map.get("account");
            String password = map.get("password");
            String autoStatus = map.get("autoStatus");
            if (null == request.getSession().getAttribute(Web.SESSION_USER)) {
                if (null != autoStatus && autoStatus.equals("1")) {
                    if (null != account && null != password && account.length() > 0 && password.length() > 0) {
                        request.getSession().setAttribute(Web.SESSION_USER, account);
                        filterChain.doFilter(request, response);
                    } else {
                        sendLogePage(request, response, filterChain);
                    }
                } else {
                    sendLogePage(request, response, filterChain);
                }
            } else if (Web.LOGIN_URL.equals(request.getServletPath())) {
                response.sendRedirect(Web.INDEX_URL);
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private HashMap<String, String> InitCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        HashMap<String, String> hashMap = new HashMap<>();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case Web.BS_COOKIE_ACCOUNT:
                        hashMap.put("account", cookie.getValue());
                        break;
                    case Web.BS_COOKIE_PASSWORD:
                        hashMap.put("password", cookie.getValue());
                        break;
                    case Web.BS_COOKIE_AUTOLOGIN:
                        hashMap.put("autoStatus", cookie.getValue());
                        break;
                }
            }
        }
        return hashMap;
    }

    private void sendLogePage(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (Web.LOGIN_URL.equals(request.getServletPath())) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(Web.LOGIN_URL);
        }
    }
}
