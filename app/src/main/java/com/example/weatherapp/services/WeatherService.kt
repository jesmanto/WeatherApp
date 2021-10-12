package com.example.weatherapp.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.weatherapp.utilities.DataUtils.cities
import com.example.weatherapp.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Jesse Okoro
 * 09/10/2021
 */
@AndroidEntryPoint
class WeatherService: Service() {
    private val binder = WeatherServiceBinder()

    inner class WeatherServiceBinder: Binder(){
        fun getServiceInstance(): WeatherService = this@WeatherService
    }

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        for (city in cities) {
        }
    }
}