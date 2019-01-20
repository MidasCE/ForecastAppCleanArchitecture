package com.example.forecastapp.di

import android.app.Application
import android.content.Context
import com.example.forecastapp.data.repository.LocationRepository
import com.example.forecastapp.domain.interactor.LocationPermissionInteractor
import com.example.forecastapp.domain.interactor.LocationPermissionInteractorImpl
import com.example.forecastapp.presentation.core.SchedulerFactory
import com.example.forecastapp.presentation.core.SchedulerFactoryImpl
import com.example.forecastapp.presentation.weather.di.WeatherActivityComponent
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module(subcomponents = [WeatherActivityComponent::class])
class ApplicationModule {

    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    @Named("main")
    fun provideMainScheduler(): Scheduler =  AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named("io")
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    fun provideLocationPermissionInteractor(locationRepository: LocationRepository): LocationPermissionInteractor
            = LocationPermissionInteractorImpl(locationRepository)

    @Provides
    @Singleton
    fun provideSchedulerFactory(@Named("io") ioScheduler : Scheduler,
                                @Named("main") mainScheduler : Scheduler): SchedulerFactory
            = SchedulerFactoryImpl(ioScheduler, mainScheduler)
}
