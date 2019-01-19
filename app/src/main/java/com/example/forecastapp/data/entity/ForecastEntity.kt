package com.example.forecastapp.data.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class ForecastEntity(
    @SerializedName("forecastday") val forecastDays: List<ForecastDetailEntity>
)

data class ForecastDetailEntity(
    @SerializedName("date") val date: Date,
    @SerializedName("day") val day: ForecastWeatherEntity
)

data class ForecastWeatherEntity(
    @SerializedName("maxtemp_c") val maxTempCelsius: Double,
    @SerializedName("mintemp_c") val minTempCelsius: Double,
    @SerializedName("avgtemp_c") val averageTempCelsius: Double
)
