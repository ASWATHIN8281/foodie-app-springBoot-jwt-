package com.stackroute.FavouriteService.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration annotation indicates that the class has @Bean definition methods
 * This is a filterconfig class
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new JwtRequestFilter());
        filter.addUrlPatterns("/api/v1/*");
        return filter;
    }
}