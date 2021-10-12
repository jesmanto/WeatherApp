package com.example.weatherapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.CityWeatherReport
import com.example.weatherapp.model.WeatherResponseBody
import com.example.weatherapp.model.WeatherRepository
import com.example.weatherapp.utilities.DataUtils
import com.example.weatherapp.utilities.DataUtils.cities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    val cityWeatherInfo: MutableLiveData<WeatherResponseBody> = MutableLiveData()
    var eatherReports: LiveData<List<CityWeatherReport>> = MutableLiveData()
    var doneFetching: MutableLiveData<Boolean> = MutableLiveData()

    init {

    }

    /**
     * Get weather report for all the cities from
     */
    fun getWeatherInformationByCity() {
        for (city in cities) {
            viewModelScope.launch(Dispatchers.IO) { weatherRepository.fetchWeatherInformation(city) }
            doneFetching.value = cities.indexOf(city) == cities.lastIndex
        }
    }

    /**
     * Fetch weather reports for all cities from database
     */
    fun getWeatherReports() = weatherRepository.readAllData
}