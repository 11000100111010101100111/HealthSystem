package com.xjh.springboot.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class BaseInterceptor extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/videos/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")+"/src/main/resources/static/res/videos/");
        super.addResourceHandlers(registry);
    }
}
