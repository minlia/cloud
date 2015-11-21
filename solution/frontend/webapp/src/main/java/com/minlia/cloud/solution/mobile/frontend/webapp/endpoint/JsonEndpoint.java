package com.minlia.cloud.solution.mobile.frontend.webapp.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonEndpoint {

    @RequestMapping("/json")
    public String hello() {
        return "{ \"message\": \"Hello Json!\" }";
    }
}
