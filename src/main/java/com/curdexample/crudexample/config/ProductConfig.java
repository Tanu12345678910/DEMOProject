package com.curdexample.crudexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
        @Bean
        public Docket api() {
                return new Docket(DocumentationType.SWAGGER_2)
                        .select()
                        .apis(RequestHandlerSelectors.any())
                        .paths(PathSelectors.any())
                        .build();
        }
     /*  public static final Contact DEFAULT_CONTACT = new Contact(
                "Vinay Kumar Prashar", "", "vinay.prashar@geminisolutions.in");
        public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
                "Employee Management Service", "Service to handle the employee related functions for the MIS 3.0", "1.0",
                "", DEFAULT_CONTACT,
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());
        private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
                new HashSet<>(Arrays.asList("application/json"));
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .groupName("All services end points")
                    .select()

                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(DEFAULT_API_INFO)
                    .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                    .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
        }*/
}
