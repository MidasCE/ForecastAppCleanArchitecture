package com.example.forecastapp.presentation.weather

interface WeatherPresenter {

    fun loadWeatherForecast()

    fun onActivityDestroy()

    fun onReceivedLocationPermissionResponse(isGranted: Boolean)
}
