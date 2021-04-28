package com.github.gahgsp.springstudies.utils;

public class TemperatureUtils {

    /**
     * Converts a given temperature from Kelvin to Celsius.
     * @param temperature a temperature value in Kelvin unit.
     * @return a formatted String containing the temperature converted to Celsius plus the unit symbol.
     */
    public static String fromKelvinToCelsius(String temperature) {
        return String.format("%.0f", Float.parseFloat(temperature) - 273.15f).concat("°C");
    }

}
