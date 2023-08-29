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
    public Cache<Object, Object> blackListConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.HOURS) // Establece el tiempo de expiración a 10 minutos (puedes ajustarlo según tus necesidades)
                .maximumSize(1000) // Establece el tamaño máximo de la caché
                .build();
    }

    @Bean
    public Cache<Object, Object> registrationCodeConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(20, TimeUnit.MINUTES) // Establece el tiempo de expiración a 10 minutos (puedes ajustarlo según tus necesidades)
                .maximumSize(1000) // Establece el tamaño máximo de la caché
                .build();
    }
}
