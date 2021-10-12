package com.example.weatherapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weatherapp.db.WeatherAppDb
import com.example.weatherapp.services.api.WeatherApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherDb: WeatherAppDb,
    private val weatherApiService: WeatherApiService
) {
    private val weatherDao = weatherDb.weatherDao()

    // Get all reports from the DB
    val readAllData: LiveData<List<CityWeatherReport>> = weatherDao.fetchWeatherReportsFromDb()

    /**
     * Fetch weather reports from the server and load the DB
     * @param city is the name of the city you wish to get its weather report
     */
    fun fetchWeatherInformation(
        city: String
    ) {
        try {
            val response = weatherApiService.getWeatherInformationByCity(
                city, "6e76055f1d172fe00a7aa9edd1f0ebf5"
            ).execute()

            if (response.isSuccessful) {
                val res = response.body()
                if (res != null) {
                    val cityWeatherReport =
                        CityWeatherReport.ModelMapper.fromWeatherResponseBody(res)

                    weatherDao.addWeatherReport(cityWeatherReport)
                }
                Log.d("RESULT", "$res")
            } else {
                Log.d("RESULT", response.message())
            }

        } catch (e: Throwable) {
            e.printStackTrace()
            Log.d("SERVER_ERROR", "An error just occurred")
        }
    }
}