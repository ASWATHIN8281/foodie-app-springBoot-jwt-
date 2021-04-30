package com.stackroute.FavouriteService.config;

import com.stackroute.UserService.config.JwtRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
