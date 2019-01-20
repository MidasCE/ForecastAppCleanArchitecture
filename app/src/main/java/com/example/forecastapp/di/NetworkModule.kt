package com.example.forecastapp.di

import android.content.Context
import com.example.forecastapp.data.WeatherAPI
import com.example.forecastapp.data.provider.LocationProvider
import com.example.forecastapp.data.provider.LocationProviderImpl
import com.example.forecastapp.data.repository.WeatherForecastRepository
import com.example.forecastapp.data.repository.WeatherForecastRepositoryImpl
import com.example.forecastapp.data.serializer.DateDeserializer
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.google.gson.GsonBuilder
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.*


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideWeatherAPI(retrofit: Retrofit): WeatherAPI = retrofit.create<WeatherAPI>(WeatherAPI::class.java)


    @Provides
    @Singleton
    fun provideLocationRepository(context: Context): LocationProvider {
        return LocationProviderImpl(context)
    }
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
            .baseUrl("http://api.apixu.com/v1/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateDeserializer())
            .create()

    @Provides
    @Singleton
    fun provideWeatherForecastRepository(weatherAPI: WeatherAPI): WeatherForecastRepository =
        WeatherForecastRepositoryImpl(weatherAPI)
}
