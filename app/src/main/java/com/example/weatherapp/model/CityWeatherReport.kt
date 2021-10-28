package com.example.weatherapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.text.SpannableStringBuilder
import com.example.weatherapp.utilities.BaseUtil
import com.example.weatherapp.utilities.BaseUtil.getImageUrl
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

/**
 * This is the model of the weather forecast object
 */
@Parcelize
@Entity(tableName = "cities_weather_report")
data class CityWeatherReport(
    @PrimaryKey(autoGenerate = false)
    @Expose
    val cityName: String,
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
    val date: Long,
    @Expose
    val sunrise: Long,
    @Expose
    val sunset: Long
) : Parcelable {
    object ModelMapper {
        /**
         * Helps to map the response body to the main data class
         * @param weatherResponseBody
         */
        fun fromWeatherResponseBody(weatherResponseBody: WeatherResponseBody) =
            CityWeatherReport(
                weatherResponseBody.name,
                weatherResponseBody.sys.country,
                weatherResponseBody.base,
                weatherResponseBody.weather[0].main,
                weatherResponseBody.weather[0].description,
                weatherResponseBody.getImageUrl(),
                weatherResponseBody.main.temp,
                weatherResponseBody.main.temp_min,
                weatherResponseBody.main.temp_max,
                weatherResponseBody.main.pressure,
                weatherResponseBody.main.humidity,
                weatherResponseBody.wind.speed,
                weatherResponseBody.dt,
                weatherResponseBody.sys.sunrise,
                weatherResponseBody.sys.sunset
            )
    }

    /**
     * This block contains all functions needed to access the
     * weather forecast values of a given city in the xml file
     */

    fun tempToString() = BaseUtil.covertTempToString(temperature) /* Get main temperature */
    fun minTempToString() = BaseUtil.covertTempToString(minTemp) /* Get minimum temperature */
    fun maxTempToString() = BaseUtil.covertTempToString(maxTemp) /* Get maximum temperature */
    fun currentDate() = BaseUtil.convertMilliSecToDate(date) /* Get current date */
    fun sunriseTime() = BaseUtil.convertMilliSecToTime(sunrise) /* Get sunrise time */
    fun sunsetTime() = BaseUtil.convertMilliSecToTime(sunset) /* Get sunset time */
    fun pressureToString() = pressure.toString() /* Get weather pressure */
    fun humidityToString() = "$humidity%" /* Get weather humidity */
    fun windSpeedToString() = windSpeed.toString() /* Get wind speed */
    fun location() = "$cityName, $country" /* Get full location */
}
