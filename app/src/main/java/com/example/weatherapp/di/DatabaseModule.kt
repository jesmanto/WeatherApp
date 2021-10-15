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

/**
 * This object house the database dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    /**
     * @param weatherAppDb
     */
    @Provides
    fun provideWeatherDao(weatherAppDb: WeatherAppDb): WeatherDao {
        return weatherAppDb.weatherDao()
    }

    /**
     * Provides a single instance of retrofit to be
     * used in the entire project
     * @param app
     */
    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): WeatherAppDb {
        return Room.databaseBuilder(
            app, WeatherAppDb::class.java, "weather_report_db"
        )
            // Allows Room to destructively recreate database
            // tables if Migrations that would migrate old database schemas to the latest schema
            // version are not found.
            .fallbackToDestructiveMigration()
            .build()
    }
}