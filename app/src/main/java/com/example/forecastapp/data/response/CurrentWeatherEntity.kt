package com.example.forecastapp.data.response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherEntity(
    @SerializedName("temp_c") val tempCelsius: Double
)
