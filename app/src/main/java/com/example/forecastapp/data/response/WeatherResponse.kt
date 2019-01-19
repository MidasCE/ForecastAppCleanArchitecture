package com.example.forecastapp.data.response

import com.google.gson.annotations.SerializedName

class WeatherResponse(
    @SerializedName("location") val location: LocationEntity,
    @SerializedName("current") val current: CurrentWeatherEntity,
    @SerializedName("forecast") val forecast: ForecastEntity?
)
