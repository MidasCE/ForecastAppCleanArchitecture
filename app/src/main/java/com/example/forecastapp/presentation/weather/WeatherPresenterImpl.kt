package com.example.forecastapp.presentation.weather

import com.example.forecastapp.domain.interactor.GetWeatherInteractor
import com.example.forecastapp.presentation.core.SchedulerFactory

class WeatherPresenterImpl(private val getWeatherInteractor: GetWeatherInteractor,
                           private val schedulerFactory: SchedulerFactory) : WeatherPresenter {

    override fun loadWeatherForecast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}