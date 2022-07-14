package com.ik.hhkt.activity.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @className: ActivityConfig
 * @author: weishihuan
 * @date: 2022-07-14 15:51
 **/
@Configuration
public class ActivityConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
