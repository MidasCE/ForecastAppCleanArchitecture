package com.example.forecastapp.presentation.weather

import com.example.forecastapp.domain.interactor.GetWeatherInteractor
import com.example.forecastapp.domain.interactor.LocationPermissionInteractor
import com.example.forecastapp.domain.model.CurrentWeather
import com.example.forecastapp.domain.model.Location
import com.example.forecastapp.domain.model.Weather
import com.example.forecastapp.presentation.core.SchedulerFactory
import com.example.forecastapp.presentation.weather.viewmodel.WeatherViewModel
import com.example.forecastapp.presentation.weather.viewmodel.mapper.WeatherViewModelMapper
import com.nhaarman.mockito_kotlin.never
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
    lateinit var locationPermissionInteractor: LocationPermissionInteractor

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

        presenter = WeatherPresenterImpl(getWeatherInteractor,
                locationPermissionInteractor,
                schedulerFactory,
                weatherViewModelMapper,
                weatherView)
    }

    @Test
    fun `Test loadWeatherForecast when permission denided`() {
        whenever(locationPermissionInteractor.isPermissionGranted()).thenReturn(false)

        presenter.loadWeatherForecast()
        verify(weatherView).navigateToPermissionSettings()
    }

    @Test
    fun `Test loadWeatherForecast receive error`() {
        whenever(locationPermissionInteractor.isPermissionGranted()).thenReturn(true)
        whenever(getWeatherInteractor.getWeatherForecast(4)).thenReturn(Single.error(Exception("error")))

        presenter.loadWeatherForecast()
        verify(weatherView).showLoading()

        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        verify(weatherView).showError()
    }

    @Test
    fun `Test loadWeatherForecast receive data`() {
        whenever(locationPermissionInteractor.isPermissionGranted()).thenReturn(true)
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

    @Test
    fun `Test onReceivedLocationPermissionResponse true`() {
        presenter.onReceivedLocationPermissionResponse(true)
        verify(weatherView, never()).showError()
    }

    @Test
    fun `Test onReceivedLocationPermissionResponse false`() {
        presenter.onReceivedLocationPermissionResponse(false)
        verify(weatherView).showError()
    }
}
