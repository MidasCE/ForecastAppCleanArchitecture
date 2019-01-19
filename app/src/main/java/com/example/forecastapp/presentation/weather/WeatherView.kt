package com.example.forecastapp.presentation.weather

import com.example.forecastapp.presentation.weather.viewmodel.WeatherViewModel

interface WeatherView {

    fun showError()

    fun showLoading()

    fun showForecastWeather(weatherViewModel: WeatherViewModel)
}