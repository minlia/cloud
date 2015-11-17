package com.kramkroc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SampleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleServiceApplication.class, args);
    }
}
