package com.example.forecastapp.di

import android.app.Activity
import com.example.forecastapp.presentation.weather.WeatherActivity
import com.example.forecastapp.presentation.weather.di.WeatherActivityComponent
import dagger.android.AndroidInjector
import dagger.android.ActivityKey
import dagger.multibindings.IntoMap
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(WeatherActivity::class)
    abstract fun bindWeatherActivity(builder: WeatherActivityComponent.Builder): AndroidInjector.Factory<out Activity>

}
