INSERT INTO OAUTH_CLIENT_DETAILS
(CLIENT_ID, CLIENT_SECRET, ACCESS_TOKEN_VALIDITY, ADDITIONAL_INFORMATION, AUTHORITIES, AUTHORIZED_GRANT_TYPES, AUTOAPPROVE, REFRESH_TOKEN_VALIDITY, RESOURCE_IDS, SCOPE, WEB_SERVER_REDIRECT_URI)
VALUES ('boot_webapp',
        -- Client password (59d14f01-38da-401c-8014-b6c0356271c8)
        '$2a$10$LCNswZlwBgyQAvNqgj35C.DuFIm4LVghVKkjS.Gyt5o8ukFHEUaMu',
        3600,
        NULL,
        -- Used when securing a url, example: .antMatcher('/data/list').access("#oauth2.clientHasRole('ROLE_TRUSTED_CLIENT')")
        'ROLE_TRUSTED_CLIENT',
        -- OAuth2 grant types enabled for this client. Possible values : password,authorization_code,refresh_token,implicit,client_credentials
        'password,refresh_token',
        NULL,
        3600,
        -- Resource ids
        'api',
        -- Scopes used when securing a url, example: .antMatchers("/**").access("#oauth2.hasScope('read')")
        'read,write',
        NULL);

-- Default user: admin/admin
INSERT INTO USER (ID, USERNAME, EMAIL, PASSWORD) VALUES ('9a820f3e-1b4a-45aa-b709-cda7957b1a75', 'admin', 'admin@admin.com', '$2a$10$D85xZKQe7AKfUWwL4altkOeYMSK/wf/eisYM7oAQoW6c42WCXlZta');

INSERT INTO ROLE (ID, ROLE_NAME) VALUES ('dc93f50a-5ac7-46ae-91cf-b0d3a4d5adc5', 'ADMIN');
INSERT INTO ROLE (ID, ROLE_NAME) VALUES ('40dc4244-c8f3-4960-9efe-03537858e84b', 'USER');

INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES ('9a820f3e-1b4a-45aa-b709-cda7957b1a75', 'dc93f50a-5ac7-46ae-91cf-b0d3a4d5adc5');
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES ('9a820f3e-1b4a-45aa-b709-cda7957b1a75', '40dc4244-c8f3-4960-9efe-03537858e84b');