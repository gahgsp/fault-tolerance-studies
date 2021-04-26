package com.github.gahgsp.springstudies.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecast {

    public String name;

    public String dt;

    public WeatherForecastMainData main;

    public String weatherMain;

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        this.weatherMain = (String) weather.get("main");
    }

}
