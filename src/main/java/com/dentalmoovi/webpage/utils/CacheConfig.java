package com.dentalmoovi.webpage.utils;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public Cache<String, String> blackListConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.HOURS) // Set expiration time
                .maximumSize(1000) // Set the max cache's size
                .build();
    }

    @Bean
    public Cache<String, String> registrationCodeConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(20, TimeUnit.MINUTES) // Set expiration time
                .maximumSize(1000) // Set the max cache's size
                .build();
    }
}
