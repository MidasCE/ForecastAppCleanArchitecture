package com.example.forecastapp.data.repository

import com.example.forecastapp.data.WeatherAPI
import com.example.forecastapp.data.entity.CurrentWeatherEntity
import com.example.forecastapp.data.entity.LocationEntity
import com.example.forecastapp.data.entity.WeatherResponse
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryTest {

    @Mock
    lateinit var weatherAPI: WeatherAPI

    private lateinit var weatherForecastRepositoryImpl : WeatherForecastRepositoryImpl

    @Before
    fun setUp() {
        weatherForecastRepositoryImpl = WeatherForecastRepositoryImpl(weatherAPI)
    }

    @Test
    fun `Test call api get response`() {
        val response = Single.just(WeatherResponse(
                LocationEntity("name", "country", Date()),
                CurrentWeatherEntity(10.0),
                null
        ))
        whenever(weatherAPI.getWeatherForecast(any(), any(), any())).thenReturn(response)

        weatherForecastRepositoryImpl.getWeatherForecast(10.0, 10.0, 1).`should equal`(response)
    }
}
