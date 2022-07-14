package com.ik.hhkt.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO
 *
 * @className: ServiceUserApplication
 * @author: weishihuan
 * @date: 2022-07-14 17:32
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.ik")
public class ServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }

}
