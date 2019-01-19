package com.example.forecastapp.data.entity

import com.google.gson.annotations.SerializedName

data class CurrentWeatherEntity(
    @SerializedName("temp_c") val tempCelsius: Double
)
