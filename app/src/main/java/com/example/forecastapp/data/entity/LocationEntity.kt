package com.example.forecastapp.data.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class LocationEntity(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("localtime") val localtime: Date
)
