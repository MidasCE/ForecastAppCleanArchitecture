package com.example.forecastapp.domain.interactor

import com.example.forecastapp.data.repository.LocationRepository

class LocationPermissionInteractorImpl(private val locationRepository: LocationRepository) : LocationPermissionInteractor {
    override fun isPermissionGranted() = locationRepository.isPermissionGranted()
}
