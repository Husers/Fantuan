package com.suz.fantuan.aop;

import com.suz.fantuan.properties.Web;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Huliang
 * On 17.3.8.
 */
@Aspect
@Component
public class ControllerAspect {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(public * com.suz.fantuan.web..*.*(..))")
    public void Point() {
    }

    @Before("Point()")
    public void doBefore() {
        ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attribute.getRequest();
        String url = String.valueOf(request.getRequestURL());
        String username = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Web.BS_COOKIE_ACCOUNT)) {
                    username = cookie.getValue();
                    break;
                }
            }
            logger.info("{} visited {}", new String[]{username, url});
        }
        logger.info("A new user visited {}", url);
    }
}
