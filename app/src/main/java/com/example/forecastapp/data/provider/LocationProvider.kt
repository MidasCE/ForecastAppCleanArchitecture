package com.example.forecastapp.data.provider

import com.example.forecastapp.data.entity.CoordinateEntity
import io.reactivex.Single

interface LocationProvider {

    fun isPermissionGranted() : Boolean

    fun getCurrentLocation() : Single<CoordinateEntity>
}