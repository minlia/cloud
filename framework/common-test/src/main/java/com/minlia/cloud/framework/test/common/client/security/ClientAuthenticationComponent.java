package com.minlia.cloud.framework.test.common.client.security;

import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import org.springframework.test.context.ActiveProfiles;

import static com.minlia.cloud.framework.common.spring.util.Profiles.CLIENT;
import static com.minlia.cloud.framework.common.spring.util.Profiles.TEST;

@Component
@ActiveProfiles({ CLIENT, TEST })
public class ClientAuthenticationComponent implements ITestAuthenticator {

    public ClientAuthenticationComponent() {
        super();
    }

    // API

    @Override
    public final RequestSpecification givenBasicAuthenticated(final String username, final String password) {
        Preconditions.checkNotNull(username);
        Preconditions.checkNotNull(password);
        return RestAssured.given().auth().preemptive().basic(username, password);
    }

}
