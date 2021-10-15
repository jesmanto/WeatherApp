package com.example.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherRepository
import com.example.weatherapp.utilities.DataUtils.cities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class handles every thing that has to do with data.
 * It's a channel with which the UI interacts with the
 * local database or remote database.
 */
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    var doneFetching: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getWeatherInformationByCity()
    }

    /**
     * Get weather report for all the cities from
     */
    private fun getWeatherInformationByCity() {
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