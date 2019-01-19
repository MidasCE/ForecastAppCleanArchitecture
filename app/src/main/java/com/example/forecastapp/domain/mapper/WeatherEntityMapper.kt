package com.example.forecastapp.domain.mapper

import com.example.forecastapp.data.entity.WeatherResponse
import com.example.forecastapp.domain.model.Weather

interface WeatherEntityMapper {
    fun mapToDomainModel(response: WeatherResponse) : Weather
}
