package com.example.forecastapp.presentation.weather

import android.app.Activity
import android.os.Bundle
import com.example.forecastapp.presentation.R
import com.example.forecastapp.presentation.weather.di.WeatherActivityComponent
import dagger.android.AndroidInjection
import javax.inject.Inject

class WeatherActivity : Activity(), WeatherView {

    @Inject
    lateinit var presenter: WeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)
        AndroidInjection.inject(this)
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
