package com.example.franchise.core.core.config;

import feign.Request;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableFeignClients("com.example.franchise.core.domain.*")
public class FeignConfig {

    @Bean
    public static Request.Options requestOptions() {
        return new Request.Options(
                60, TimeUnit.SECONDS,
                60, TimeUnit.SECONDS,
                false);
    }
}