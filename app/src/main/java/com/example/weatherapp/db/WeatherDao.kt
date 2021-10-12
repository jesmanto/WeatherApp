package com.example.weatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.model.CityWeatherReport

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWeatherReport(cityWeatherReport: CityWeatherReport)

    @Query("SELECT * FROM cities_weather_report ORDER BY city ASC ")
    fun fetchWeatherReportsFromDb(): LiveData<List<CityWeatherReport>>
}