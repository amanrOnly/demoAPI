package com.dtdl.demoAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import org.modelmapper.ModelMapper;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dtdl.demoAPI"))
                .paths(PathSelectors.any())
                .build().apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("Aman", "https://github.com/amanrOnly", "amanr.primary@gmail.com");
        return new ApiInfoBuilder()
                .title("DTDL")
                .description("DemoAPI as a training project on Spring Boot")
                .version("0.1.0")
                .license("Aman")
                .licenseUrl("https://www.google.com/")
                .contact(contact)
                .build();
    }

//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }

}