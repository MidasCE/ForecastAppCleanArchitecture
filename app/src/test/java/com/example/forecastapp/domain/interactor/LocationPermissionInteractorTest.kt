package com.example.forecastapp.domain.interactor

import com.example.forecastapp.data.repository.LocationRepository
import com.nhaarman.mockito_kotlin.whenever
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationPermissionInteractorTest {

    @Mock
    lateinit var locationRepository: LocationRepository

    private lateinit var locationPermissionInteractorImpl: LocationPermissionInteractorImpl

    @Before
    fun setUp() {
        locationPermissionInteractorImpl = LocationPermissionInteractorImpl(locationRepository)
    }

    @Test
    fun `Test isPermissionGranted return data from repository`() {
        whenever(locationRepository.isPermissionGranted()).thenReturn(true)

        locationPermissionInteractorImpl.isPermissionGranted().`should equal`(true)
    }

}
