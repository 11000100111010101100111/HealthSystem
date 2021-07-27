package com.xjh.springboot.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//全局配置类
/*
    @Auto:肖家海
    @Time:2021-07-27 10:37:00

    配置跨域请求：
    允许全局下全部访问路径跨域
    允许get，post，put，options，delete类型请求跨域
    允许携带信息
    设置最大跨域时间为3600毫秒
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**").
                allowedOrigins("http://localhost:8080",null).
                allowedMethods("GET","POST","PUT","OPTIONS","DELETE").
                allowCredentials(true).
                maxAge(3600);
    }
}
