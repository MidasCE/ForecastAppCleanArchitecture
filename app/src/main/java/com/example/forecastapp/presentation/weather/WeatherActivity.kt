package com.example.forecastapp.presentation.weather

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.forecastapp.presentation.R
import com.example.forecastapp.presentation.weather.viewmodel.ForecastWeatherViewModel
import com.example.forecastapp.presentation.weather.viewmodel.WeatherViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class WeatherActivity : Activity(), WeatherView {

    @Inject
    lateinit var presenter: WeatherPresenter

    @Inject
    lateinit var adapter: ForecastWeatherListAdapter

    private lateinit var errorView: ViewGroup
    private lateinit var reportView: ViewGroup
    private lateinit var loadingView: ViewGroup
    private lateinit var locationTextView: TextView
    private lateinit var currentTempTextView: TextView
    private lateinit var retryButton: Button
    private lateinit var weatherRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)
        AndroidInjection.inject(this)
        initView()
        presenter.loadWeatherForecast()
    }

    private fun initView() {
        errorView = findViewById(R.id.errorView)
        reportView = findViewById(R.id.reportView)
        loadingView = findViewById(R.id.loadingView)
        locationTextView = findViewById(R.id.locationTextView)
        currentTempTextView = findViewById(R.id.currentTempTextView)
        weatherRecyclerView = findViewById(R.id.weatherRecyclerView)
        retryButton = findViewById(R.id.retryButton)

        retryButton.setOnClickListener {
            presenter.loadWeatherForecast()
        }

        weatherRecyclerView.layoutManager = LinearLayoutManager(this)
        weatherRecyclerView.adapter = adapter
    }

    override fun showError() {
        errorView.visibility = VISIBLE
        reportView.visibility = GONE
        loadingView.visibility = GONE
    }

    override fun showLoading() {
        loadingView.visibility = VISIBLE
        errorView.visibility = GONE
        reportView.visibility = GONE
    }

    override fun showForecastWeather(weatherViewModel: WeatherViewModel) {
        reportView.visibility = VISIBLE
        loadingView.visibility = GONE
        errorView.visibility = GONE

        updateCurrentWeather(weatherViewModel)
        updateForecastWeatherList(weatherViewModel.forecastWeatherViewModelList)
    }

    private fun updateCurrentWeather(weatherViewModel: WeatherViewModel) {
        locationTextView.text = weatherViewModel.locationName
        currentTempTextView.text = weatherViewModel.currentTemperature
    }

    private fun updateForecastWeatherList(list: List<ForecastWeatherViewModel>) {
        adapter.weatherList = list
        adapter.notifyDataSetChanged()
    }
}
