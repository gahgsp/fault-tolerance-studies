package com.github.gahgsp.springstudies.controller.weather;

import com.github.gahgsp.springstudies.model.weather.WeatherForecast;
import com.github.gahgsp.springstudies.service.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/weather")
public class WeatherController {

    @Autowired
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{cityName}")
    public WeatherForecast weatherForecast(@PathVariable String cityName) {
        return weatherService.retrieveCurrentWeatherDataByCity(cityName);
    }
}
