package com.example.forecastapp.presentation.weather.viewmodel

data class WeatherViewModel (
    val locationName: String,
    val currentTemperature: String,
    val forecastWeatherViewModelList: List<ForecastWeatherViewModel>
)
