package com.example.weatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.model.CityWeatherReport
import com.example.weatherapp.model.WeatherResponseBody

@Database(entities = [CityWeatherReport::class], version = 2, exportSchema = false)
abstract class WeatherAppDb: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}