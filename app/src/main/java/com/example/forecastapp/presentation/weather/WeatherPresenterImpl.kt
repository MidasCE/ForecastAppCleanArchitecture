package com.example.forecastapp.presentation.weather

import com.example.forecastapp.domain.interactor.GetWeatherInteractor
import com.example.forecastapp.domain.interactor.LocationPermissionInteractor
import com.example.forecastapp.presentation.core.SchedulerFactory
import com.example.forecastapp.presentation.weather.viewmodel.mapper.WeatherViewModelMapper
import io.reactivex.disposables.CompositeDisposable

class WeatherPresenterImpl(private val getWeatherInteractor: GetWeatherInteractor,
                           private val locationPermissionInteractor: LocationPermissionInteractor,
                           private val schedulerFactory: SchedulerFactory,
                           private val weatherViewModelMapper: WeatherViewModelMapper,
                           private val weatherView: WeatherView) : WeatherPresenter {

    private var compositeDisposable = CompositeDisposable()

    override fun loadWeatherForecast() {
        if (locationPermissionInteractor.isPermissionGranted()) {
            weatherView.showLoading()
            val disposable = getWeatherInteractor.getWeatherForecast(4)
                    .subscribeOn(schedulerFactory.io())
                    .observeOn(schedulerFactory.main())
                    .subscribe({ weather ->
                        weatherView.showForecastWeather(weatherViewModelMapper.mapToViewModel(weather))
                    }, {
                        weatherView.showError()
                    })

            compositeDisposable.add(disposable)
        } else {
            weatherView.navigateToPermissionSettings()
        }
    }

    override fun onReceivedLocationPermissionResponse(isGranted: Boolean) {
        if (isGranted) {
            loadWeatherForecast()
        } else {
            weatherView.showError()
        }
    }

    override fun onActivityDestroy() {
        compositeDisposable.clear()
    }

}
