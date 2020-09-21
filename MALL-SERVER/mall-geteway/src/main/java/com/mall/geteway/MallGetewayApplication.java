package com.mall.geteway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MallGetewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallGetewayApplication.class, args);
    }

}
