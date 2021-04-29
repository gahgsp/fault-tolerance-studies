# Spring Reactive (WebFlux): Caching & Fault Tolerance

This projected was developed to serve as an example on a simple implementation of WebFlux with features such as Caching and Fault Tolerance.

## Features:

### Caching

For caching purposes, I used the Caffeine library due to it's popularity and also ease of use.

This cache is also used for a long term caching feature that provides information from already searched cities in case of this service is unavailable.

### Fault Tolerance:

Here I used the out-of-the-box capabilities from the WebFlux library, where you can handle dynamically errors and success based on the request response.

In this feature we use the long term cache implemented using the Caffeine library to get information from previous searched cities.

## Integration:

This project integrates the OpenWeatherApi to retrieve the weather data based on a request for a specific city in the world.

## APIs:

### Health Check:

You can use `GET /about` to check if the service is up and running.

### Open Weather API:

You can use `GET /weather/{city}` to retrieve the weather data from a city anywhere in the world. 