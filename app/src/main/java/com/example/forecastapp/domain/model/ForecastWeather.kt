package com.example.forecastapp.domain.model

import java.util.*

data class ForecastWeather(
    val date: Date,
    val maxTempCelsius: Double,
    val minTempCelsius: Double,
    val averageTempCelsius: Double
)
