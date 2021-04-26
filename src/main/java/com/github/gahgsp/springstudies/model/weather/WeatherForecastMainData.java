package com.github.gahgsp.springstudies.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastMainData {

    public String temp;

    public String pressure;

    public String humidity;

    public String temp_min;

    public String temp_max;
}
