package com.dentalmoovi.webpage.services;

import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;

@Service
public class CacheSer {
    
    private Cache<String, String> blackListConfig;
    private Cache<String, String> registrationCodeConfig;

    public CacheSer(Cache<String, String> blackListConfig, Cache<String, String> registrationCodeConfig) {
        this.blackListConfig = blackListConfig;
        this.registrationCodeConfig = registrationCodeConfig;
    }

    public void addToOrUpdateBlackListCache(String key, String value) {
        blackListConfig.put(key, value);
    }

    public String getFromBlackListCache(String key) {
        return blackListConfig.getIfPresent(key);
    }

    public void removeFromBlackListCache(String key) {
        blackListConfig.invalidate(key);
    }


    public void addToOrUpdateRegistrationCache(String key, String value) {
        registrationCodeConfig.put(key, value);
    }

    public String getFromRegistrationCache(String key) {
        return registrationCodeConfig.getIfPresent(key);
    }

    public void removeFromRegistrationCache(String key) {
        registrationCodeConfig.invalidate(key);
    }
}
