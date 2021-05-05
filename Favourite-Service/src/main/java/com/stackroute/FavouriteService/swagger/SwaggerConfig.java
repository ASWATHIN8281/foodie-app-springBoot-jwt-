package com.stackroute.FavouriteService.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * This is swagger configuration class
 * @EnableSwagger2 is used to enable swagger
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket postApi(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("swagger-api-Foodie-App").
                apiInfo(apiInfo()).select().paths((com.google.common.base.Predicate<String>) postPaths()).build();
    }

    private Predicate<String> postPaths(){
        return or(regex("/api/v1/.*"),regex("/api/v1/.*"));
    }
    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder().title("Favourite List")
                .description("Favourite List of Foodies")
                .contact("jenis29@gmail.com")
                .license("Foodie App")
                .licenseUrl("www.foodie-app.com")
                .termsOfServiceUrl("foodie-app@gmail.com")
                .version("1.0").build();
    }


}

