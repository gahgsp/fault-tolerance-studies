package com.github.gahgsp.springstudies.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecast {

    private String city;

    private Instant dateTime;

    private WeatherForecastMainData details;

    @JsonProperty("weatherCondition")
    private String weatherMain;

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        this.weatherMain = (String) weather.get("main");
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.city = name;
    }

    @JsonProperty("dt")
    public void setDt(String dt) {
        this.dateTime = Instant.ofEpochSecond(Long.parseLong(dt));
    }

    @JsonProperty("main")
    public void setMain(WeatherForecastMainData main) {
        this.details = main;
    }

    public String getCity() {
        return city;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public WeatherForecastMainData getDetails() {
        return details;
    }

    public String getWeatherMain() {
        return weatherMain;
    }
}