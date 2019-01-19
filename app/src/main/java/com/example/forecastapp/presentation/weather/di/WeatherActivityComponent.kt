package com.example.forecastapp.presentation.weather.di

import com.example.forecastapp.presentation.weather.WeatherActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [WeatherActivityModule::class])
interface WeatherActivityComponent : AndroidInjector<WeatherActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<WeatherActivity>()
}
