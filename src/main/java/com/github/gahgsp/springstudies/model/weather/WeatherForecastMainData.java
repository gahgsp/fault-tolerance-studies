package com.github.gahgsp.springstudies.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.gahgsp.springstudies.utils.TemperatureUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastMainData {

    private String temperature;

    private String pressure;

    private String humidity;

    private String minimumTemperature;

    private String maximumTemperature;

    @JsonProperty("temp")
    public void setTemp(String temperature) {
        this.temperature = TemperatureUtils.fromKelvinToCelsius(temperature);
    }

    @JsonProperty("pressure")
    public void setPressure(String pressure) {
        this.pressure = pressure.concat("hPa");
    }

    @JsonProperty("humidity")
    public void setHumidity(String humidity) {
        this.humidity = humidity.concat("%");
    }

    @JsonProperty("temp_min")
    public void setTempMin(String tempMin) {
        this.minimumTemperature = TemperatureUtils.fromKelvinToCelsius(tempMin);
    }

    @JsonProperty("temp_max")
    public void setTempMax(String tempMax) {
        this.maximumTemperature = TemperatureUtils.fromKelvinToCelsius(tempMax);
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getMinimumTemperature() {
        return minimumTemperature;
    }

    public String getMaximumTemperature() {
        return maximumTemperature;
    }
}
