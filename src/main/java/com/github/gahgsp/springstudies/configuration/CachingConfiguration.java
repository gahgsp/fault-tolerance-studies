package com.github.gahgsp.springstudies.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CachingConfiguration {

    /**
     * This is the basic cache we will use when making requests constantly for the same city.
     */
    public static final String SHORT_TERM_CACHE = "cities";

    /**
     * This is the cache that will be storing the values for a longer period. His goal is to provide information
     * when some error occur during the request to the server.
     * This way we can provide information about the weather even when the server is down.
     */
    public static final String LONG_TERM_CACHE = "citiesFallback";

    @Bean
    @Qualifier(SHORT_TERM_CACHE)
    @Primary
    public CacheManager cities() {
        // Defining the amount of time a register will be cached before we expire it...
        Caffeine caffeineCacheConfiguration = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES);
        // Defining the names of the cache groups we will handle...
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(SHORT_TERM_CACHE);
        cacheManager.setCaffeine(caffeineCacheConfiguration);
        return cacheManager;
    }

    @Bean
    @Qualifier(LONG_TERM_CACHE)
    public CacheManager citiesFallback() {
        // Defining the amount of time a register will be cached before we expire it...
        Caffeine caffeineCacheConfiguration = Caffeine.newBuilder().expireAfterWrite(500, TimeUnit.MINUTES);
        // Defining the names of the cache groups we will handle...
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(LONG_TERM_CACHE);
        cacheManager.setCaffeine(caffeineCacheConfiguration);
        return cacheManager;
    }
}
