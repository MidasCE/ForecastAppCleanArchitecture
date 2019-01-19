package com.example.forecastapp.presentation.weather

interface WeatherView {

    fun showError()

    fun showLoading()

    fun showData()
}