package com.example.weatherapp.model

/**
 * This is the model of the weather forecast response payload
 * coming from the server. This is the object that will later
 * be mapped to the main weather forecast object
 */
data class WeatherResponseBody(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Long,
    val id: Int,
    val main: Main,
    val name: String,
    val rain: Rain,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
) {
    /**
     * Builds the weather icon URL link using the weather icon
     */
    fun getImageUrl() = "http://openweathermap.org/img/wn/${weather[0].icon}@2x.png"
}