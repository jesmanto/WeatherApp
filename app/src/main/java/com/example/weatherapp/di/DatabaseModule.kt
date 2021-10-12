package com.example.weatherapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.weatherapp.db.WeatherAppDb
import com.example.weatherapp.db.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideWeatherDao(weatherAppDb: WeatherAppDb): WeatherDao {
        return weatherAppDb.weatherDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): WeatherAppDb {
        return Room.databaseBuilder(
            app, WeatherAppDb::class.java, "weather_report_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}