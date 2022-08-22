package com.curdexample.crudexample.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class ProductConfig {
    public static final Contact DEFAULT_CONTACT = new Contact(
            "Tanu Jain", "", "tanu.jain@geminisolutions.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "SPRING BOOT CRUD API", "Demo Project to learn about RestAPi in spring boot,actuators ,swaggers and many more", "1.0",
            "", DEFAULT_CONTACT,
            " ", " ", Arrays.asList());
    public static final ApiInfo DEFAULT_API_INFO_ACTUATORS = new ApiInfo(
            "Awards and Events Service Health Point", "Actuators end point to check the health of the application.", "1.0",
            "", DEFAULT_CONTACT,
            "", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<>(Arrays.asList("application/json"));

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.curdexample.crudexample.Controller"))
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("All services end points")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.curdexample.crudexample.Controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

    @Bean
    public Docket actuatorsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Health Information")
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.curdexample.crudexample.Controller"))
                .build().apiInfo(DEFAULT_API_INFO_ACTUATORS);
    }
}