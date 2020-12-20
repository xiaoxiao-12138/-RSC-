package com.springboot.express.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/customer/login");
        registry.addRedirectViewController("/customer","/customer/login");
        registry.addRedirectViewController("/customer/","/customer/login");
        //registry.addViewController("/postman").setViewName("/postman/login");
        //registry.addViewController("/postman/").setViewName("/postman/login");
        registry.addRedirectViewController("/postman","/postman/login");
        registry.addRedirectViewController("/postman/","/postman/login");
        registry.addRedirectViewController("/admin","/admin/login");
        registry.addRedirectViewController("/admin/","/admin/login");
        //registry.addRedirectViewController("/error/404","/error/404");
        registry.addViewController("/error/404").setViewName("/error/404");
    }

    //拦截器规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/customer/**")    //拦截指定路径
                .excludePathPatterns("/customer/addCustomer","/customer/register","/error/404","/","/customer","/customer/","/customer/loginVerify","/customer/login","/static/**");   //不拦截指定路径

        registry.addInterceptor(new LoginHandlerInterceptorPostman())
                .addPathPatterns("/postman/**")     //拦截指定路径
                .excludePathPatterns("/postman","/postman/","/postman/loginVerify","/postman/login","/static/**");   //不拦截指定路径

        registry.addInterceptor(new LoginHandlerInterceptorAdmin())
                .addPathPatterns("/admin/**")     //拦截指定路径
                .excludePathPatterns("/admin","/admin/","/admin/loginVerify","/admin/login","/static/**");   //不拦截指定路径
    }

}
