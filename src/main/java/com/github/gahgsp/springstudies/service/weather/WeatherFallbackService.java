package com.github.gahgsp.springstudies.service.weather;

import com.github.gahgsp.springstudies.model.weather.WeatherForecast;
import com.github.gahgsp.springstudies.model.weather.WeatherForecastException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.github.gahgsp.springstudies.configuration.CachingConfiguration.LONG_TERM_CACHE;

@Service
public class WeatherFallbackService {

    @Autowired
    @Qualifier(LONG_TERM_CACHE)
    private CacheManager cacheManager;

    public void addWeatherDataToFallbackCache(String cityName, WeatherForecast weatherForecast) {
        cacheManager.getCache(LONG_TERM_CACHE).put(cityName, weatherForecast);
    }

    public Mono<WeatherForecast> retrieveWeatherFromFallback(String cityName) {
        Cache fallbackCache = cacheManager.getCache(LONG_TERM_CACHE);
        if (fallbackCache != null && fallbackCache.get(cityName) != null) {
            return Mono.just((WeatherForecast) fallbackCache.get(cityName).get());
        } else {
            return Mono.error(new WeatherForecastException("We were not able to retrieve the data from: [" + cityName + "]!"));
        }
    }

}
