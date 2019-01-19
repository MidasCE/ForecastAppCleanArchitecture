package com.example.forecastapp.data

import com.example.forecastapp.data.response.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    companion object {
        const val API_KEY = "fcf787aaf02346f3be341031191901"
    }

    @GET("current.json")
    fun getCurrentWeather(@Query("key") key: String = API_KEY, @Query("q") q: String?): Single<WeatherResponse>

    @GET("forecast.json")
    fun getWeatherForecast(@Query("key") key: String = API_KEY, @Query("q") q: String?, @Query("days") days: Int):
            Single<WeatherResponse>

}