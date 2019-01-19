package com.example.forecastapp.presentation.weather.viewmodel

import org.parceler.Parcel

@Parcel
data class ForecastWeatherViewModel(
    val date: String,
    val temperature: String
)
