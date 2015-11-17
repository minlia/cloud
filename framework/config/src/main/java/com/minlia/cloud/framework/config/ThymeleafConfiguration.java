/**
 * Copyright (C) 2004-2015 http://oss.minlia.com/license/framework/2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//package com.mycompany.myapp.config;
//
//import org.apache.commons.lang.CharEncoding;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.*;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//
//@Configuration
//public class ThymeleafConfiguration {
//
//    private final Logger log = LoggerFactory.getLogger(ThymeleafConfiguration.class);
//
//    @Bean
//    @Description("Thymeleaf template resolver serving HTML 5 emails")
//    public ClassLoaderTemplateResolver emailTemplateResolver() {
//        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
//        emailTemplateResolver.setPrefix("mails/");
//        emailTemplateResolver.setSuffix(".html");
//        emailTemplateResolver.setTemplateMode("HTML5");
//        emailTemplateResolver.setCharacterEncoding(CharEncoding.UTF_8);
//        emailTemplateResolver.setOrder(1);
//        return emailTemplateResolver;
//    }
//
//    @Bean
//    @Description("Spring mail message resolver")
//    public MessageSource emailMessageSource() {
//        log.info("loading non-reloadable mail messages resources");
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:/mails/messages/messages");
//        messageSource.setDefaultEncoding(CharEncoding.UTF_8);
//        return messageSource;
//    }
//}
