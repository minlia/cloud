/**
 * Copyright (C) 2004-2015 http://oss.minlia.com/license/solution/usercenter/2015
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
package com.minlia.cloud.infrastructure.framework.usercenter.config;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.jayway.restassured.RestAssured;
import com.minlia.cloud.infrastructure.framework.usercenter.constants.ApiConstants;
import com.minlia.cloud.infrastructure.framework.usercenter.entities.CredentialsUserProfile;
import com.minlia.cloud.infrastructure.framework.usercenter.test.AbstractIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import static com.jayway.restassured.RestAssured.given;


@TestPropertySource(locations="classpath:throttling.properties")
@DatabaseSetup("classpath:testdata/auth.xml")
public class ThrottlingInterceptorTest extends AbstractIntegrationTest {

    @Before
    public void setUp() {
        RestAssured.port = getPort();
    }


    @Test
    public void throttlingProtectionAuthenticatedUser() {
        String accessToken = authenticate();

        given()
                .header("Authorization", "Bearer " + accessToken)
                .get(ApiConstants.API_PREFIX + "/profile")
                .then()
                .log()
                .all()
                .statusCode(200);

        given()
                .header("Authorization", "Bearer " + accessToken)
                .get(ApiConstants.API_PREFIX + "/profile")
                .then()
                .log()
                .all()
                .statusCode(403);
    }


    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "classpath:testdata/create_user_result.xml")
    public void throttlingProtectionRegister() {
        CredentialsUserProfile profile = new CredentialsUserProfile("testUser", "testUser@test.com", "testPassword".toCharArray());

        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic Ym9vdF93ZWJhcHA6NTlkMTRmMDEtMzhkYS00MDFjLTgwMTQtYjZjMDM1NjI3MWM4")
                .body(profile)
                .post(ApiConstants.API_PREFIX + "/profile")
                .then()
                .log()
                .all()
                .statusCode(200);

        profile = new CredentialsUserProfile("testUser2", "testUser2@test.com", "testPassword".toCharArray());

        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic Ym9vdF93ZWJhcHA6NTlkMTRmMDEtMzhkYS00MDFjLTgwMTQtYjZjMDM1NjI3MWM4")
                .body(profile)
                .post(ApiConstants.API_PREFIX + "/profile")
                .then()
                .log()
                .all()
                .statusCode(403);
    }

}
