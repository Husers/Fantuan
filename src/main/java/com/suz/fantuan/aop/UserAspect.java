package com.suz.fantuan.aop;

import com.suz.fantuan.model.User;
import com.suz.fantuan.utils.MealsInit;
import com.suz.fantuan.utils.Utils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by Huliang
 * On 17.3.8.
 */
@Aspect
@Component
public class UserAspect {
    private static final Logger logger = LoggerFactory.getLogger(UserAspect.class);

    private final Utils utils;
    private final MealsInit mealsInit;

    @Autowired
    public UserAspect(MealsInit mealsInit, Utils utils) {
        this.mealsInit = mealsInit;
        this.utils = utils;
    }

    @Pointcut("execution(public * com.suz.fantuan.service.Impl.UserServiceImpl.registerUser(..))")
    public void registerUser() {
    }

    @Pointcut("execution(public * com.suz.fantuan.service.Impl.UserServiceImpl.checkPassword(..))")
    public void checkPassword() {
    }

    @Pointcut("execution(public * com.suz.fantuan.service.Impl.UserServiceImpl.modifyPassword(..))")
    public void modifyPassword() {
    }

    private String username;

    @Before("registerUser() && args(user)")
    public void registerUserBefore(User user) {
        logger.info("{} registered account", user.getUsername());
    }

    @Around("registerUser()")
    public Object registerUserAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        User user = (User) args[0];
        Method[] methods = MealsInit.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(utils.TGet(user.getUsername()))) {
                user.setMeals((String) method.invoke(mealsInit));
                break;
            }
        }
        return pjp.proceed(args);
    }

    @Before("checkPassword() && args(user)")
    public void checkPasswordBefore(User user) {
        username = user.getUsername();
    }

    @AfterReturning(returning = "result", pointcut = "checkPassword()")
    public void checkPasswordAfterReturning(boolean result) {
        if (result) {
            logger.info("{} successful login", username);
        } else {
            logger.info("{} failed to log in");
        }
    }

    @Before("modifyPassword() && args(user)")
    public void modifyPasswordBefore(User user) {
    }
}
