package com.suz.fantuan.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Huliang
 * On 17.3.8.
 */
@Component
@PropertySource("classpath:data.properties")
public class MealsInit {
    @Value("${huliang}")
    private String huliang;
    @Value("${lifaqiang}")
    private String lifaqiang;
    @Value("${niechao}")
    private String niechao;
    @Value("${shenzhen}")
    private String shenzhen;
    @Value("${zhouxueqing}")
    private String zhouxueqing;

    public String getHuliang() {
        return huliang;
    }

    public void setHuliang(String huliang) {
        this.huliang = huliang;
    }

    public String getLifaqiang() {
        return lifaqiang;
    }

    public void setLifaqiang(String lifaqiang) {
        this.lifaqiang = lifaqiang;
    }

    public String getNiechao() {
        return niechao;
    }

    public void setNiechao(String niechao) {
        this.niechao = niechao;
    }

    public String getShenzhen() {
        return shenzhen;
    }

    public void setShenzhen(String shenzhen) {
        this.shenzhen = shenzhen;
    }

    public String getZhouxueqing() {
        return zhouxueqing;
    }

    public void setZhouxueqing(String zhouxueqing) {
        this.zhouxueqing = zhouxueqing;
    }
}
