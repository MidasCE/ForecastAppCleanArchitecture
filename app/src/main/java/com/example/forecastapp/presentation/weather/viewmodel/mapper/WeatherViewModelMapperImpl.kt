package com.example.forecastapp.presentation.weather.viewmodel.mapper

import com.example.forecastapp.domain.model.Weather
import com.example.forecastapp.presentation.weather.viewmodel.ForecastWeatherViewModel
import com.example.forecastapp.presentation.weather.viewmodel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModelMapperImpl : WeatherViewModelMapper {
    override fun mapToViewModel(weather: Weather): WeatherViewModel {
        return WeatherViewModel(
            weather.location.name,
            weather.current.tempCelsius.toString() + "\u00B0",
            weather.forecast.map {
                val sdf = SimpleDateFormat("EEEE", Locale.US)
                ForecastWeatherViewModel(
                    sdf.format(it.date),
                    it.averageTempCelsius.toString() + " C"
                )
            })
    }
}
