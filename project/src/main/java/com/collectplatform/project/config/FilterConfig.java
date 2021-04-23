package com.collectplatform.project.config;

import com.collectplatform.project.filter.DownloadedFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/23
 */
//@Configuration
//public class FilterConfig {
//    @Bean
//    public FilterRegistrationBean<DownloadedFilter> registFilter() {
//        FilterRegistrationBean<DownloadedFilter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(new DownloadedFilter());
//        registration.addUrlPatterns("/file/download");
//        registration.setName("DownloadedFilter");
//        registration.setOrder(1);
//        return registration;
//    }
//}
