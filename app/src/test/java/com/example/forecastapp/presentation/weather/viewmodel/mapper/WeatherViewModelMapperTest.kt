package com.example.forecastapp.presentation.weather.viewmodel.mapper

import com.example.forecastapp.domain.model.CurrentWeather
import com.example.forecastapp.domain.model.ForecastWeather
import com.example.forecastapp.domain.model.Location
import com.example.forecastapp.domain.model.Weather
import com.example.forecastapp.presentation.weather.viewmodel.ForecastWeatherViewModel
import com.example.forecastapp.presentation.weather.viewmodel.WeatherViewModel
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModelMapperTest {

    private lateinit var weatherViewModelMapperImpl: WeatherViewModelMapperImpl

    @Before
    fun setUp() {
        weatherViewModelMapperImpl = WeatherViewModelMapperImpl()
    }

    @Test
    fun `Test map to view model with empty forecast data`() {
        val domainModel = Weather(
                Location("name", "country", Date()),
                CurrentWeather(10.0),
                emptyList()
        )

        val result = weatherViewModelMapperImpl.mapToViewModel(domainModel)

        result.`should equal`(WeatherViewModel(
                domainModel.location.name,
                domainModel.current.tempCelsius.toString() + "\u00B0",
                emptyList()
        ))
    }

    @Test
    fun `Test map to view model with forecast data`() {
        val forecastDate = Date()
        val domainModel = Weather(
                Location("name", "country", Date()),
                CurrentWeather(10.0),
                mutableListOf(
                        ForecastWeather(
                                forecastDate,
                                12.0,
                                10.0,
                                11.0
                        )
                )
        )

        val result = weatherViewModelMapperImpl.mapToViewModel(domainModel)

        result.`should equal`(WeatherViewModel(
                domainModel.location.name,
                domainModel.current.tempCelsius.toString() + "\u00B0",
                mutableListOf(
                        ForecastWeatherViewModel(
                                SimpleDateFormat("EEEE", Locale.US).format(forecastDate),
                                11.0.toString() + " C"
                        )
                )
        ))
    }

}
