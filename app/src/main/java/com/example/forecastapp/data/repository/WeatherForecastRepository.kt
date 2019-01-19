package com.example.forecastapp.data.repository

import com.example.forecastapp.data.response.WeatherResponse
import io.reactivex.Single

interface WeatherForecastRepository {

    fun getCurrentWeather(latitude: Double, longitude: Double) : Single<WeatherResponse>

    fun getWeatherForecast(latitude: Double, longitude: Double, days: Int) : Single<WeatherResponse>
}
