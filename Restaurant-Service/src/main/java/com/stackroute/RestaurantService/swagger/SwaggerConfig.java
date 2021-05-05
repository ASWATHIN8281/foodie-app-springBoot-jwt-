package com.stackroute.RestaurantService.swagger;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Configuration annotation indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.
 */
@Configuration
/**
 * @EnableSwagger2 annotation is used to enable the Swagger2 for your Spring Boot application
 */
@EnableSwagger2

public class SwaggerConfig {

    @Bean
    public Docket postApi(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("swagger-api-RestaurantService-FoodieApp").
                apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths(){
        return or(regex("/api/v1/.*"),regex("/api/v1/.*"));
    }
    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder().title("Restaurant  Service For Foodie App  Rest API Documentation")
                .description("This is the description for restaurant service used in foodie app")
                .contact("allbyantony178@gmail.com")
                .license("stackroute")
                .licenseUrl("www.stackroute.com")
                .termsOfServiceUrl("aswathi@gmail.com")
                .version("1.0").build();
    }


}
