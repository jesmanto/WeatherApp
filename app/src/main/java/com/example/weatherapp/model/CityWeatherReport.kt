package com.example.weatherapp.model

import android.os.Parcelable
import android.text.style.SuperscriptSpan
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.text.SpannableStringBuilder
import android.text.Spanned
import com.example.weatherapp.utilities.BaseUtil
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cities_weather_report")
data class CityWeatherReport(
    @PrimaryKey(autoGenerate = false)
    @Expose
    val city: String,
    @Expose
    val country: String,
    @Expose
    val base: String,
    @Expose
    val mainWeather: String,
    @Expose
    val weatherDescription: String,
    @Expose
    val weatherIcon: String,
    @Expose
    val temperature: Double,
    @Expose
    val minTemp: Double,
    @Expose
    val maxTemp: Double,
    @Expose
    val pressure: Int,
    @Expose
    val humidity: Int,
    @Expose
    val windSpeed: Double,
    @Expose
    val sunrise: Long,
    @Expose
    val sunset: Long
): Parcelable {
    object ModelMapper {
        /**
         * Helps to map the
         */
        fun fromWeatherResponseBody(weatherResponseBody: WeatherResponseBody) =
            CityWeatherReport(
                weatherResponseBody.name,
                weatherResponseBody.sys.country,
                weatherResponseBody.base,
                weatherResponseBody.weather[0].main,
                weatherResponseBody.weather[0].description,
                "http://openweathermap.org/img/wn/${weatherResponseBody.weather[0].icon}@2x.png",
                weatherResponseBody.main.temp,
                weatherResponseBody.main.temp_min,
                weatherResponseBody.main.temp_max,
                weatherResponseBody.main.pressure,
                weatherResponseBody.main.humidity,
                weatherResponseBody.wind.speed,
                weatherResponseBody.sys.sunrise,
                weatherResponseBody.sys.sunset
            )
    }

    fun tempToString(): SpannableStringBuilder {
        return BaseUtil.covertTempToString(temperature)
    }

    fun minTempToString(): SpannableStringBuilder {
        return BaseUtil.covertTempToString(minTemp)
    }

    fun maxTempToString(): SpannableStringBuilder {
        return BaseUtil.covertTempToString(maxTemp)
    }

    fun sunriseTime(): String{
        return BaseUtil.convertMilliSecToDate(sunrise)
    }

    fun sunsetTime(): String{
        return BaseUtil.convertMilliSecToDate(sunset)
    }

    fun pressureToString() = pressure.toString()
    fun humidityToString() = "$humidity%"
    fun windSpeedToString() = windSpeed.toString()
    fun location() = "$city, $country"
}
