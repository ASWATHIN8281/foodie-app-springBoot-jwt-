package com.stackroute.RestaurantService.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Indicates this as a configuration class
 */

@Configuration
public class FilterConfig {
    /**
     *  Create a bean for FilterRegistrationBean.
     *  1. Register the JwtFilter
     *  2. add URL pattern for all requests so that any request for
     *     that URL pattern will be intercepted by the filter
     */

    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new JwtRequestFilter());
        filter.addUrlPatterns("/api/v1/*");
        return filter;
    }
}
