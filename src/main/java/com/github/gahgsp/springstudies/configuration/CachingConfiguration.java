package com.github.gahgsp.springstudies.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CachingConfiguration {

    @Bean
    public CacheManager cacheManager() {
        // Defining the amount of time a register will be cached before we expire it...
        Caffeine caffeineCacheConfiguration = Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS);
        // Defining the names of the cache groups we will handle...
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("cities");
        cacheManager.setCaffeine(caffeineCacheConfiguration);
        return cacheManager;
    }
}
