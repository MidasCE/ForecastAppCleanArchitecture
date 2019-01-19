package com.example.forecastapp.domain.model

data class Weather(
    val location: Location,
    val current: CurrentWeather,
    val forecast: List<ForecastWeather>
)
