package com.minlia.cloud.infrastructure.dashboard.cloud;

import com.github.vanroy.cloud.dashboard.config.EnableCloudDashboard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@SpringBootApplication
//@EnableHystrixDashboard
//public class HystrixDashboardApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(HystrixDashboardApplication.class, args);
//	}
//
//}
@SpringBootApplication
//@EnableEurekaServer
@EnableDiscoveryClient
@EnableCloudDashboard
public class SpringCloudDashboardApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDashboardApplication.class, args);
	}
}
