package com.example.forecastapp.domain.mapper

import com.example.forecastapp.data.entity.WeatherResponse
import com.example.forecastapp.domain.model.*

class WeatherEntityMapperImpl : WeatherEntityMapper {
    override fun mapToDomainModel(response: WeatherResponse): Weather =
        Weather(
            Location(response.location.name, response.location.country, response.location.localtime),
            CurrentWeather(response.current.tempCelsius),
            response.forecast?.forecastDays?.map {
                ForecastWeather(it.date, it.day.maxTempCelsius, it.day.minTempCelsius, it.day.averageTempCelsius)
            } ?: emptyList()
        )

}
