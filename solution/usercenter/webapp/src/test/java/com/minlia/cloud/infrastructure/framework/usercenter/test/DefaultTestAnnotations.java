package com.minlia.cloud.infrastructure.framework.usercenter.test;

import com.minlia.cloud.infrastructure.framework.usercenter.UserCenterApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserCenterApplication.class)
@WebIntegrationTest(randomPort = true)
@TestPropertySource(locations="classpath:test.properties")
public @interface DefaultTestAnnotations { }
