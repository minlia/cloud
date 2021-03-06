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
package com.minlia.cloud.infrastructure.framework.usercenter.rest.controllers;


import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.jayway.restassured.RestAssured;
import com.minlia.cloud.infrastructure.framework.usercenter.constants.ApiConstants;
import com.minlia.cloud.infrastructure.framework.usercenter.entities.CredentialsUserProfile;
import com.minlia.cloud.infrastructure.framework.usercenter.test.AbstractIntegrationTest;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;


@DatabaseSetup("classpath:testdata/auth.xml")
public class ProfileControllerTest extends AbstractIntegrationTest {

    @Before
    public void setUp() {
        RestAssured.port = getPort();
    }


    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "classpath:testdata/create_user_result.xml")
    public void register() {

        CredentialsUserProfile profile = new CredentialsUserProfile("testUser", "testUser@test.com", "testPassword".toCharArray());

        // Register
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic Ym9vdF93ZWJhcHA6NTlkMTRmMDEtMzhkYS00MDFjLTgwMTQtYjZjMDM1NjI3MWM4")
                .body(profile)
                .post(ApiConstants.API_PREFIX + "/profile")
                .then()
                .log()
                .all()
                .statusCode(200);

        // Authenticate with the new user
        given()
                .header("Authorization", "Basic Ym9vdF93ZWJhcHA6NTlkMTRmMDEtMzhkYS00MDFjLTgwMTQtYjZjMDM1NjI3MWM4")
                .formParam("grant_type", "password")
                .formParam("username", "testUser")
                .formParam("password", "testPassword")
                .post("/oauth/token")
                .then()
                .statusCode(200);
    }


    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "classpath:testdata/create_user_failed.xml")
    public void registerExistentUserAndFail() {
        CredentialsUserProfile profile = new CredentialsUserProfile("admin", "testUser@test.com", "otherPassword".toCharArray());

        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic Ym9vdF93ZWJhcHA6NTlkMTRmMDEtMzhkYS00MDFjLTgwMTQtYjZjMDM1NjI3MWM4")
                .body(profile)
                .post(ApiConstants.API_PREFIX + "/profile")
                .then()
                .log()
                .all()
                .statusCode(400);
    }


    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "classpath:testdata/create_user_failed.xml")
    public void registerWithoutUsernameAndFail() {
        CredentialsUserProfile profile = new CredentialsUserProfile(null, "test@test.com", "otherPassword".toCharArray());

        // Create user
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic Ym9vdF93ZWJhcHA6NTlkMTRmMDEtMzhkYS00MDFjLTgwMTQtYjZjMDM1NjI3MWM4")
                .body(profile)
                .post(ApiConstants.API_PREFIX + "/profile")
                .then()
                .log()
                .all()
                .statusCode(400);
    }


    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "classpath:testdata/create_user_failed.xml")
    public void registerWithoutEmailAndFail() {
        CredentialsUserProfile profile = new CredentialsUserProfile("testUser", null, "otherPassword".toCharArray());

        // Create user
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic Ym9vdF93ZWJhcHA6NTlkMTRmMDEtMzhkYS00MDFjLTgwMTQtYjZjMDM1NjI3MWM4")
                .body(profile)
                .post(ApiConstants.API_PREFIX + "/profile")
                .then()
                .log()
                .all()
                .statusCode(400);
    }


    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "classpath:testdata/create_user_failed.xml")
    public void registerWrongEmailFormatAndFail() {
        CredentialsUserProfile profile = new CredentialsUserProfile("testUser", "email", "otherPassword".toCharArray());

        // Create user
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic Ym9vdF93ZWJhcHA6NTlkMTRmMDEtMzhkYS00MDFjLTgwMTQtYjZjMDM1NjI3MWM4")
                .body(profile)
                .post(ApiConstants.API_PREFIX + "/profile")
                .then()
                .log()
                .all()
                .statusCode(400);
    }


    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "classpath:testdata/create_user_failed.xml")
    public void registerExistentEmailAndFail() {
        CredentialsUserProfile profile = new CredentialsUserProfile("testUser", "admin@admin.com", "otherPassword".toCharArray());

        // Create user
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic Ym9vdF93ZWJhcHA6NTlkMTRmMDEtMzhkYS00MDFjLTgwMTQtYjZjMDM1NjI3MWM4")
                .body(profile)
                .post(ApiConstants.API_PREFIX + "/profile")
                .then()
                .log()
                .all()
                .statusCode(400);
    }


    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "classpath:testdata/create_user_failed.xml")
    public void registerUnknownClientAndFail() {
        CredentialsUserProfile profile = new CredentialsUserProfile("testUser", "test@test.com", "otherPassword".toCharArray());

        // Create user
        given()
                .header("Content-Type", "application/json")
                .body(profile)
                .post(ApiConstants.API_PREFIX + "/profile")
                .then()
                .log()
                .all()
                .statusCode(401);
    }


    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "classpath:testdata/create_user_failed.xml")
    public void registerWrongClientCredentialsAndFail() {
        CredentialsUserProfile profile = new CredentialsUserProfile("testUser", "test@test.com", "otherPassword".toCharArray());

        // Create user
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic Ym9vdF93ZWJhcHA6d3JvbmdQYXNzd29yZA==")
                .body(profile)
                .post(ApiConstants.API_PREFIX + "/profile")
                .then()
                .log()
                .all()
                .statusCode(401);
    }

}
