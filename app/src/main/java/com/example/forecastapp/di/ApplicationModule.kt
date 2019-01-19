package com.example.forecastapp.di

import android.app.Application
import android.content.Context
import com.example.forecastapp.AndroidApplication
import com.example.forecastapp.presentation.weather.di.WeatherActivityComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [WeatherActivityComponent::class])
class ApplicationModule {

    @Provides
    fun provideContext(application: Application): Context = application
}
