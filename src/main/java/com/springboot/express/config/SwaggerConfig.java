package com.springboot.express.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lbl
 * @create 2020-08-03 17:41
 */
@Configuration//配置类
@EnableSwagger2 //swagger注解
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("断舍离")
                .apiInfo(webApiInfo())
                .select()
                //RequestHandlerSelectors配置要扫描的方式
                // basePackage扫描指定的包
                //any()扫描全部
                //none()都不扫描
                // .apis(RequestHandlerSelectors.basePackage("com.atguigu.eduservice.controller.EduTeacherController"))
                //.paths(Predicates.not(PathSelectors.regex("/admin/.*")))//path：过滤路径
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    private ApiInfo webApiInfo() {

        return new ApiInfoBuilder()
                .title("springboot课设")
                .description("快递系统")
                .version("1.0")
                .build();
    }
}
