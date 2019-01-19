package com.example.forecastapp.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.support.v4.content.ContextCompat
import com.example.forecastapp.data.entity.CoordinateEntity
import io.reactivex.Single
import java.lang.Exception

class LocationRepositoryImpl(private val context: Context) : LocationRepository {

    private val requiredPermissions =
        listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    private var locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as android.location.LocationManager

    override fun getCurrentLocation(): Single<CoordinateEntity> {
        if (isPermissionGranted()) {
            if (!isLocationServiceEnabled())
                return Single.error(
                    Exception(
                        "Service Disabled"
                    )
                )

            var location: Location? = null

            if (isNetworkProviderEnabled()) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            } else if (isGpsProviderEnabled()) {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            }

            return location?.let {
                Single.just(CoordinateEntity(it.latitude, it.longitude))
            } ?: Single.error(Exception("Can't get location"))

        } else {
            return Single.error(
                Exception(
                    "Require permissions: $requiredPermissions"
                )
            )
        }
    }

    private fun isPermissionGranted(): Boolean {
        for (permission in requiredPermissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }

    private fun isLocationServiceEnabled(): Boolean {
        return isGpsProviderEnabled() || isNetworkProviderEnabled()
    }

    private fun isNetworkProviderEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun isGpsProviderEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}
