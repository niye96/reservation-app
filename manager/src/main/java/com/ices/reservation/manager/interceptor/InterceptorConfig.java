package com.ices.reservation.manager.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 14:43 2018/5/8 0008
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Bean
    public BaseInterceptor baseInterceptor(){
        return new BaseInterceptor();
    }


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        List<String> whiteUrl = new ArrayList<>();
        whiteUrl.add("/login/buser");
        whiteUrl.add("/user/login");
        whiteUrl.add("/user/register");
        whiteUrl.add("/code/address/pagelist");
        whiteUrl.add("/code/address/list");
        whiteUrl.add("/code/adddress/detail");
        whiteUrl.add("/hospital/hospital/pagelist");
        whiteUrl.add("/hospital/doctor/detail");
        whiteUrl.add("/hospital/department/pagelist");
        whiteUrl.add("/hospital/calendar/pagelist");

        registry.addInterceptor(baseInterceptor())
            .addPathPatterns("/**").excludePathPatterns(whiteUrl);
    }
}
