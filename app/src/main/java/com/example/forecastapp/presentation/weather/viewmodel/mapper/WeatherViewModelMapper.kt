package com.example.forecastapp.presentation.weather.viewmodel.mapper

import com.example.forecastapp.domain.model.Weather
import com.example.forecastapp.presentation.weather.viewmodel.WeatherViewModel

interface WeatherViewModelMapper {
    fun mapToViewModel(weather: Weather) : WeatherViewModel
}
