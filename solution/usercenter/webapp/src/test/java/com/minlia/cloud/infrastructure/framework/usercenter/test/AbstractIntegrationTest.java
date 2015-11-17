package com.minlia.cloud.infrastructure.framework.usercenter.test;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.minlia.cloud.infrastructure.framework.usercenter.UserCenterApplication;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


import static com.jayway.restassured.RestAssured.given;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserCenterApplication.class)
@WebIntegrationTest(randomPort = true)
@TestPropertySource(locations="classpath:test.properties")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public abstract class AbstractIntegrationTest {

    @Value("${local.server.port}")
    private int port;

    protected int getPort() {
        return port;
    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("============ Starting test: " + description.getMethodName());
        }
    };


    protected String authenticate() {
        ResponseBodyExtractionOptions body = given()
                .header("Authorization", "Basic Ym9vdF93ZWJhcHA6NTlkMTRmMDEtMzhkYS00MDFjLTgwMTQtYjZjMDM1NjI3MWM4")
                .formParam("grant_type", "password")
                .formParam("username", "admin")
                .formParam("password", "admin")
                .post("/oauth/token")
                .then()
                .statusCode(200)
                .extract().body();

        return body.jsonPath().get("access_token");
    }

}
