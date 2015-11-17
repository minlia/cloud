package com.minlia.cloud.infrastructure.dashboard.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
@ComponentScan(value = {"de.codecentric.boot.admin"})
public class SpringBootAdminDashboard {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminDashboard.class, args);
    }
}
