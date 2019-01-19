package com.example.forecastapp.domain.interactor

import com.example.forecastapp.domain.model.Weather
import io.reactivex.Single

interface GetWeatherInteractor {
    fun getWeatherForecast(days: Int) : Single<Weather>
}
