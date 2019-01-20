package com.example.forecastapp.domain.interactor

import com.example.forecastapp.data.provider.LocationProvider

class LocationPermissionInteractorImpl(private val locationRepository: LocationProvider) : LocationPermissionInteractor {
    override fun isPermissionGranted() = locationRepository.isPermissionGranted()
}
