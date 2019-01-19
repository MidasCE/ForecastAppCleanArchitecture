package com.example.forecastapp.domain.interactor

import com.example.forecastapp.data.repository.WeatherForecastRepository
import com.example.forecastapp.domain.mapper.WeatherEntityMapper
import com.example.forecastapp.domain.model.Weather
import io.reactivex.Single

class GetWeatherInteractorImpl(private val weatherForecastRepository: WeatherForecastRepository,
                               private val weatherEntityMapper: WeatherEntityMapper) : GetWeatherInteractor {

    override fun getWeatherForecast(latitude: Double, longitude: Double, days: Int): Single<Weather> =
        weatherForecastRepository.getWeatherForecast(latitude, longitude, days).map {
            weatherEntityMapper.mapToDomainModel(it)
        }

}
