package com.example.forecastapp.presentation.weather.viewmodel.mapper

import com.example.forecastapp.domain.model.Weather
import com.example.forecastapp.presentation.weather.viewmodel.ForecastWeatherViewModel
import com.example.forecastapp.presentation.weather.viewmodel.WeatherViewModel
import java.util.*

class WeatherViewModelMapperImpl : WeatherViewModelMapper {
    override fun mapToViewModel(weather: Weather): WeatherViewModel {
        return WeatherViewModel(
            weather.location.name,
            weather.current.tempCelsius.toString(),
            weather.forecast.map {
                val calendar = Calendar.getInstance()
                calendar.time = it.date
                ForecastWeatherViewModel(
                    calendar.get(Calendar.DAY_OF_WEEK).toString(),
                    it.averageTempCelsius.toString()
                )
            })
    }
}
