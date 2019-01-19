package com.example.forecastapp.data.repository

import com.example.forecastapp.data.entity.CoordinateEntity
import io.reactivex.Single

interface LocationRepository {

    fun getCurrentLocation() : Single<CoordinateEntity>
}