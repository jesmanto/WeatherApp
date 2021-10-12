package com.example.weatherapp.services.api

import com.example.weatherapp.model.CityWeatherReport
import com.example.weatherapp.model.WeatherResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/data/2.5/weather")
    fun getWeatherInformationByCity(
        @Query("q") city: String,
        @Query("appId") appId: String
    ): Call<WeatherResponseBody>
}