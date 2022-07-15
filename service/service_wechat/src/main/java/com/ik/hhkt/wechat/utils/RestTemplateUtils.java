package com.ik.hhkt.wechat.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @className: RestTemplateUtils
 * @author: weishihuan
 * @date: 2022-07-15 14:50
 **/
@Configuration
public class RestTemplateUtils {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
