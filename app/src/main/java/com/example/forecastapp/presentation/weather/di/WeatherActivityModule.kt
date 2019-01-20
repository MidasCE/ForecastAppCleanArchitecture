package com.example.forecastapp.presentation.weather.di

import com.example.forecastapp.data.provider.LocationProvider
import com.example.forecastapp.data.repository.WeatherForecastRepository
import com.example.forecastapp.domain.interactor.GetWeatherInteractor
import com.example.forecastapp.domain.interactor.GetWeatherInteractorImpl
import com.example.forecastapp.domain.interactor.LocationPermissionInteractor
import com.example.forecastapp.domain.mapper.WeatherEntityMapper
import com.example.forecastapp.domain.mapper.WeatherEntityMapperImpl
import com.example.forecastapp.presentation.core.SchedulerFactory
import com.example.forecastapp.presentation.weather.*
import com.example.forecastapp.presentation.weather.viewmodel.mapper.WeatherViewModelMapper
import com.example.forecastapp.presentation.weather.viewmodel.mapper.WeatherViewModelMapperImpl
import dagger.Module
import dagger.Provides

@Module
class WeatherActivityModule {

    @Provides
    fun provideWeatherView(weatherActivity: WeatherActivity) : WeatherView = weatherActivity

    @Provides
    fun provideWeatherPresenter(getWeatherInteractor: GetWeatherInteractor,
                                locationPermissionInteractor: LocationPermissionInteractor,
                                schedulerFactory: SchedulerFactory,
                                weatherViewModelMapper: WeatherViewModelMapper,
                                weatherView: WeatherView): WeatherPresenter
            = WeatherPresenterImpl(getWeatherInteractor, locationPermissionInteractor, schedulerFactory,
            weatherViewModelMapper, weatherView)

    @Provides
    fun provideGetWeatherInteractor(weatherForecastRepository: WeatherForecastRepository,
                                    locationRepository: LocationProvider,
                                    weatherEntityMapper: WeatherEntityMapper): GetWeatherInteractor
            = GetWeatherInteractorImpl(weatherForecastRepository, locationRepository, weatherEntityMapper)

    @Provides
    fun provideForecastWeatherListAdapter(): ForecastWeatherListAdapter = ForecastWeatherListAdapter()

    @Provides
    fun provideWeatherViewModelMapper(): WeatherViewModelMapper = WeatherViewModelMapperImpl()

    @Provides
    fun provideWeatherEntityMapper(): WeatherEntityMapper = WeatherEntityMapperImpl()

}
