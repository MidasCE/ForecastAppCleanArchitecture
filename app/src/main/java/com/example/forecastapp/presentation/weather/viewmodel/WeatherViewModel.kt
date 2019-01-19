package com.example.forecastapp.presentation.weather.viewmodel

import org.parceler.Parcel

@Parcel
data class WeatherViewModel (
    var locationName: String,
    var currentTemperature: String,
    var forecastWeatherViewModelList: List<ForecastWeatherViewModel>
)
