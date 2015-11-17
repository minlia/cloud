package com.minlia.cloud.framework.web.config;


import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalDate;

import com.minlia.cloud.framework.common.constants.Constants.Profiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.fasterxml.classmate.TypeResolver;
/**
 * Created by user on 11/14/15.
 */
@Configuration
@EnableSwagger2
@Profile(value = Profiles.DEVELOPMENT)
public class Swagger2Config {

    @Autowired
    private TypeResolver typeResolver;
//
//    @Bean
//    public Docket greetingApi() {
//        return new Docket(DocumentationType.SPRING_WEB)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .pathMapping("/")
//                .directModelSubstitute(LocalDate.class, String.class)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .alternateTypeRules(newRule(typeResolver.resolve(DeferredResult.class,
//                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
//                        typeResolver.resolve(WildcardType.class)))
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET,
//                        newArrayList(new ResponseMessageBuilder()
//                                .code(500)
//                                .message("500 message")
//                                .responseModel(new ModelRef("Error"))
//                                .build()))
//                .enableUrlTemplating(true);
//    }


        @Bean
        public Docket documentation() {
            return new Docket(DocumentationType.SPRING_WEB)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.regex("/api/.*"))
                    .build()
                    .pathMapping("/")
                    .apiInfo(metadata());
        }

        @Bean
        public UiConfiguration uiConfig() {
            return UiConfiguration.DEFAULT;
        }

        private ApiInfo metadata() {
            return new ApiInfoBuilder()
                    .title("Minlia Cloud Development Environment System API")
                    .description("Minlia Cloud Development Environment System API")
                    .version("2.0")
                    .contact("cloud@minlia.com")
                    .build();
        }
}
