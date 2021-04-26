package com.github.gahgsp.springstudies.service.weather;

import com.github.gahgsp.springstudies.model.weather.WeatherForecast;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;

    @Value("${openweather.api.key}")
    private String apiKey;

    public WeatherService() {
        this.webClient = WebClient.builder()
                .baseUrl("api.openweathermap.org/data/2.5/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<WeatherForecast> retrieveCurrentWeatherByCity(String cityName) {
        return webClient.get()
                .uri("weather?q=" + cityName + "&appid=" + apiKey)
                .retrieve()
                .bodyToMono(WeatherForecast.class);
    }

}
