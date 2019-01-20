package com.example.forecastapp.domain.mapper

import com.example.forecastapp.data.entity.*
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import java.util.*

class WeatherEnitityMapperTest {

    private lateinit var weatherEntityMapperImpl: WeatherEntityMapperImpl

    @Before
    fun setUp() {
        weatherEntityMapperImpl = WeatherEntityMapperImpl()
    }

    @Test
    fun `Test map to domain model`() {
        val localTime = Date()
        val forecastDate = Date()
        val response = WeatherResponse(
                LocationEntity("name", "country", localTime),
                CurrentWeatherEntity(10.0),
                ForecastEntity(
                        mutableListOf(ForecastDetailEntity(
                                forecastDate,
                                ForecastWeatherEntity(
                                        12.0,
                                        9.0,
                                        11.0
                                )
                        ))
                )
        )
        val result = weatherEntityMapperImpl.mapToDomainModel(response)
        result.location.name.`should equal`("name")
        result.location.country.`should equal`("country")
        result.location.localtime.`should equal`(localTime)
        result.current.tempCelsius.`should equal`(10.0)
        result.forecast[0].date.`should equal`(forecastDate)
        result.forecast[0].maxTempCelsius.`should equal`(12.0)
        result.forecast[0].minTempCelsius.`should equal`(9.0)
        result.forecast[0].averageTempCelsius.`should equal`(11.0)
    }

    @Test
    fun `Test map to domain model with null forecast data`() {
        val localTime = Date()
        val response = WeatherResponse(
                LocationEntity("name", "country", localTime),
                CurrentWeatherEntity(10.0),
                null
        )
        val result = weatherEntityMapperImpl.mapToDomainModel(response)
        result.location.name.`should equal`("name")
        result.location.country.`should equal`("country")
        result.location.localtime.`should equal`(localTime)
        result.current.tempCelsius.`should equal`(10.0)
        result.forecast.size`should equal`(0)
    }

}
