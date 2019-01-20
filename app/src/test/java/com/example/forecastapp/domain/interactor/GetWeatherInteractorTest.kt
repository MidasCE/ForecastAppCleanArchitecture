package com.example.forecastapp.domain.interactor

import com.example.forecastapp.data.entity.CoordinateEntity
import com.example.forecastapp.data.entity.CurrentWeatherEntity
import com.example.forecastapp.data.entity.LocationEntity
import com.example.forecastapp.data.entity.WeatherResponse
import com.example.forecastapp.data.repository.LocationRepository
import com.example.forecastapp.data.repository.WeatherForecastRepository
import com.example.forecastapp.domain.mapper.WeatherEntityMapper
import com.example.forecastapp.domain.model.CurrentWeather
import com.example.forecastapp.domain.model.Location
import com.example.forecastapp.domain.model.Weather
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class GetWeatherInteractorTest {

    @Mock
    lateinit var weatherForecastRepository: WeatherForecastRepository

    @Mock
    lateinit var locationRepository: LocationRepository

    @Mock
    lateinit var weatherEntityMapper: WeatherEntityMapper

    @Mock
    lateinit var observer: SingleObserver<Weather>

    private lateinit var getWeatherInteractorImpl: GetWeatherInteractorImpl

    internal var scheduler = TestScheduler()

    @Before
    fun setUp() {
        getWeatherInteractorImpl = GetWeatherInteractorImpl(weatherForecastRepository, locationRepository, weatherEntityMapper)
    }

    @Test
    fun `Test getWeatherForecast return weather domain model`() {
        val response = WeatherResponse(
                LocationEntity("name", "country", Date()),
                CurrentWeatherEntity(10.0),
                null
        )
        val domainModel = Weather(
                Location("name", "country", Date()),
                CurrentWeather(10.0),
                emptyList()
        )

        whenever(locationRepository.getCurrentLocation()).thenReturn(Single.just(CoordinateEntity(10.0, 10.0)))
        whenever(weatherForecastRepository.getWeatherForecast(10.0, 10.0, 4)).thenReturn(Single.just(response))
        whenever(weatherEntityMapper.mapToDomainModel(response)).thenReturn(domainModel)

        getWeatherInteractorImpl.getWeatherForecast(4)
                .subscribeOn(scheduler)
                .subscribe(observer)

        scheduler.triggerActions()

        verify(locationRepository).getCurrentLocation()
        verify(weatherForecastRepository).getWeatherForecast(10.0, 10.0, 4)

        verify(observer).onSuccess(any(Weather::class.java))
    }

}
