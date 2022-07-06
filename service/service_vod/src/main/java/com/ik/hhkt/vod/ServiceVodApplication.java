package com.ik.hhkt.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO
 *
 * @className: ServiceVodApplication
 * @author: weishihuan
 * @date: 2022-07-06 10:40
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.ik")
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class,args);
    }
}
