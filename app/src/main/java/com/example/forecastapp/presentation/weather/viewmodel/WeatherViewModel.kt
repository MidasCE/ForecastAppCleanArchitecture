package com.example.forecastapp.presentation.weather.viewmodel

import org.parceler.Parcel

@Parcel
data class WeatherViewModel (
    val locationName: String,
    val currentTemperature: String,
    val forecastWeatherViewModelList: List<ForecastWeatherViewModel>
)
