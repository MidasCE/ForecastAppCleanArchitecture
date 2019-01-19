package com.example.forecastapp.presentation.weather.di

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
    fun provideWeatherPresenter(): WeatherPresenter = WeatherPresenterImpl()

}
