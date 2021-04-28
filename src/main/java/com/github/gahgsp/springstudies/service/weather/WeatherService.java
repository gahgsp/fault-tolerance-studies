package com.github.gahgsp.springstudies.service.weather;

import com.github.gahgsp.springstudies.model.weather.WeatherForecast;
import com.github.gahgsp.springstudies.model.weather.WeatherForecastException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.github.gahgsp.springstudies.configuration.CachingConfiguration.LONG_TERM_CACHE;
import static com.github.gahgsp.springstudies.configuration.CachingConfiguration.SHORT_TERM_CACHE;

@Service
public class WeatherService {

    @Autowired
    @Qualifier(LONG_TERM_CACHE)
    private CacheManager cacheManager;

    @Autowired
    private WeatherFallbackService weatherFallbackService;

    private final WebClient webClient;

    @Value("${openweather.api.key}")
    private String apiKey;

    public WeatherService() {
        this.webClient = WebClient.builder()
                .baseUrl("api.openweathermap.org/data/2.5/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Cacheable(SHORT_TERM_CACHE)
    public Mono<WeatherForecast> retrieveCurrentWeatherByCity(String cityName) {
        System.out.println("Requesting information about the city: " + cityName);
        return webClient.get()
                .uri("weather?q=" + cityName + "&appid=" + apiKey)
                .retrieve()
                .bodyToMono(WeatherForecast.class)
                .doOnSuccess(weatherForecast -> weatherFallbackService.addWeatherDataToFallbackCache(cityName, weatherForecast))
                .onErrorResume(ex -> weatherFallbackService.retrieveWeatherFromFallback(cityName));
    }


}
