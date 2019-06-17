package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer		//当前项目启用Eureka服务器
public class SpringCloudEurekaServer {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaServer.class, args);

	}

}
