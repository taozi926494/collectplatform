package com.collectplatform.project.config;

import com.collectplatform.project.filter.TempFileInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/25
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private TempFileInterceptor tempFileInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tempFileInterceptor);
    }
}
