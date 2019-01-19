package com.example.forecastapp.data.repository

import com.example.forecastapp.data.WeatherAPI
import com.example.forecastapp.data.entity.WeatherResponse
import io.reactivex.Single

class WeatherForecastRepositoryImpl(private val api: WeatherAPI) : WeatherForecastRepository {

    override fun getWeatherForecast(latitude: Double, longitude: Double, days: Int): Single<WeatherResponse> =
        api.getWeatherForecast(q = latitude.toString() + "," + longitude.toString(), days = days)

}
