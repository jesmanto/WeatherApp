package com.example.weatherapp.di

import androidx.multidex.BuildConfig
import com.example.weatherapp.services.api.WeatherApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * This is the object the houses all the dependencies needed in the entire project
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * An OkHttp interceptor which logs request and response information
     */
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    /**
     * Factory for calls, which can be used to send HTTP requests and read their responses.
     */
    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    /**
     * Provides Gson for the conversion of Json file to data class
     */
    @Provides
    fun provideGson(): Gson{
        return GsonBuilder()
            .setDateFormat("yyyy.MM.dd")
            .create()
    }

    /**
     * Provides a single instance of retrofit to be
     * used in the entire project
     */
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): WeatherApiService{
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(WeatherApiService::class.java)
    }
}