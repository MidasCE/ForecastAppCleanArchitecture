package com.example.forecastapp.presentation.weather

import com.example.forecastapp.domain.interactor.GetWeatherInteractor
import com.example.forecastapp.domain.model.CurrentWeather
import com.example.forecastapp.domain.model.Location
import com.example.forecastapp.domain.model.Weather
import com.example.forecastapp.presentation.core.SchedulerFactory
import com.example.forecastapp.presentation.weather.viewmodel.WeatherViewModel
import com.example.forecastapp.presentation.weather.viewmodel.mapper.WeatherViewModelMapper
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class WeatherPresenterTest {

    @Mock
    lateinit var getWeatherInteractor: GetWeatherInteractor

    @Mock
    lateinit var schedulerFactory: SchedulerFactory

    @Mock
    lateinit var weatherViewModelMapper: WeatherViewModelMapper

    @Mock
    lateinit var weatherView: WeatherView

    private lateinit var ioScheduler: TestScheduler

    private lateinit var mainScheduler: TestScheduler

    private lateinit var presenter : WeatherPresenterImpl

    @Before
    fun setUp() {
        ioScheduler = TestScheduler()
        mainScheduler = TestScheduler()

        whenever(schedulerFactory.io()).thenReturn(ioScheduler)
        whenever(schedulerFactory.main()).thenReturn(mainScheduler)

        presenter = WeatherPresenterImpl(getWeatherInteractor, schedulerFactory, weatherViewModelMapper, weatherView)
    }

    @Test
    fun `Test loadWeatherForecast receive error`() {
        whenever(getWeatherInteractor.getWeatherForecast(4)).thenReturn(Single.error(Exception("error")))

        presenter.loadWeatherForecast()
        verify(weatherView).showLoading()

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(weatherView).showError()
    }

    @Test
    fun `Test loadWeatherForecast receive data`() {
        val domainModel = Weather(
                Location("name", "country", Date()),
                CurrentWeather(10.0),
                emptyList()
        )

        val viewModel = WeatherViewModel(
                domainModel.location.name,
                domainModel.current.tempCelsius.toString() + "\u00B0",
                emptyList()
        )

        whenever(getWeatherInteractor.getWeatherForecast(4)).thenReturn(Single.just(domainModel))
        whenever(weatherViewModelMapper.mapToViewModel(domainModel)).thenReturn(viewModel)

        presenter.loadWeatherForecast()
        verify(weatherView).showLoading()

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(weatherView).showForecastWeather(viewModel)
    }
}
