package com.example.forecastapp.presentation.weather.di

import com.example.forecastapp.data.repository.WeatherForecastRepository
import com.example.forecastapp.domain.interactor.GetWeatherInteractor
import com.example.forecastapp.domain.interactor.GetWeatherInteractorImpl
import com.example.forecastapp.domain.mapper.WeatherEntityMapper
import com.example.forecastapp.domain.mapper.WeatherEntityMapperImpl
import com.example.forecastapp.presentation.weather.WeatherActivity
import com.example.forecastapp.presentation.weather.WeatherPresenter
import com.example.forecastapp.presentation.weather.WeatherPresenterImpl
import com.example.forecastapp.presentation.weather.WeatherView
import dagger.Module
import dagger.Provides

@Module
class WeatherActivityModule {

    @Provides
    fun provideWeatherView(weatherActivity: WeatherActivity) : WeatherView= weatherActivity

    @Provides
    fun provideWeatherPresenter(getWeatherInteractor: GetWeatherInteractor): WeatherPresenter
            = WeatherPresenterImpl(getWeatherInteractor)

    @Provides
    fun provideGetWeatherInteractor(weatherForecastRepository: WeatherForecastRepository,
                                    weatherEntityMapper: WeatherEntityMapper): GetWeatherInteractor
            = GetWeatherInteractorImpl(weatherForecastRepository, weatherEntityMapper)

    @Provides
    fun provideWeatherEntityMapper(): WeatherEntityMapper
            = WeatherEntityMapperImpl()

}
