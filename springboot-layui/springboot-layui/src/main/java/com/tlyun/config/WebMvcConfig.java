package com.tlyun.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 朝花不迟暮
 * @version 1.0
 * @date 2021/6/26 20:18
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.tlyun.mapper")
public class WebMvcConfig implements WebMvcConfigurer
{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        /*解释:
        /files/**: 相当于 别名的意思
        file:D:/图片/: 本地文件的路径*/
        String property = System.getProperty("user.dir");
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:"+property+"/src/main/resources/static/files/");
        // WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}

