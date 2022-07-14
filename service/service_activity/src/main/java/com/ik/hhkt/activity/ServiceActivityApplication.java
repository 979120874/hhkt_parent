package com.ik.hhkt.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @className: ServiceActivityApplication
 * @author: weishihuan
 * @date: 2022-07-14 15:39
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ik")
@ComponentScan(basePackages = "com.ik")
public class ServiceActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceActivityApplication.class, args);
    }
}
