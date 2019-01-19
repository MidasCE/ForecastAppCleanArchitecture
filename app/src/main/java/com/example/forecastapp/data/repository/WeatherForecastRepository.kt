package com.example.forecastapp.data.repository

import com.example.forecastapp.data.entity.WeatherResponse
import io.reactivex.Single

interface WeatherForecastRepository {

    fun getWeatherForecast(latitude: Double, longitude: Double, days: Int) : Single<WeatherResponse>
}
