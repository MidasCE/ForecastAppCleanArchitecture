package com.example.forecastapp.domain.model

import java.util.*

data class Location(
    val name: String,
    val country: String,
    val localtime: Date
)
