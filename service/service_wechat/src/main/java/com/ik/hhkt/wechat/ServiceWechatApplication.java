package com.ik.hhkt.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO
 *
 * @className: ServiceWechatApplication
 * @author: weishihuan
 * @date: 2022-07-14 22:14
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ik")
@ComponentScan(basePackages = "com.ik")
public class ServiceWechatApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceWechatApplication.class, args);
    }
}
