package com.example.forecastapp.presentation.weather

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.forecastapp.presentation.R
import com.example.forecastapp.presentation.weather.viewmodel.ForecastWeatherViewModel

class ForecastWeatherListAdapter(private val weatherList: List<ForecastWeatherViewModel>)
    : RecyclerView.Adapter<ForecastWeatherListAdapter.ViewHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastWeatherListAdapter.ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_forecast_weather, null)
        itemLayoutView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        return ViewHolder(itemLayoutView)
    }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: ForecastWeatherListAdapter.ViewHolder, position: Int) {
        holder.dateTextView.text = weatherList[position].date
        holder.tempTextView.text = weatherList[position].temperature
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val tempTextView: TextView = itemView.findViewById(R.id.tempTextView)

    }
}