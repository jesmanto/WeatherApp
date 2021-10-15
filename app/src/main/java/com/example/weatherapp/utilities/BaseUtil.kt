package com.example.weatherapp.utilities

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.SuperscriptSpan
import java.text.SimpleDateFormat

object BaseUtil {
    /**
     * Converts temperature from fahrenheit degree celsius and add the unit
     * @param temp in Double
     * @return converted temperature in SpannableString
     */
    fun covertTempToString(temp: Double): SpannableStringBuilder {
        val superscriptSpan = SuperscriptSpan()
        val builder = SpannableStringBuilder("${temp.toInt() - 273.0}oC")
        builder.setSpan(
            superscriptSpan,
            "${temp.toInt() - 273.0}oC".indexOf("o"),
            "${temp.toInt() - 273.0}oC".indexOf("o") + "o".length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return builder
    }

    /**
     * Converts date in milliseconds to a 12-hour time
     * with format (hour:minute:sec)
     * @param timeInMill in Long
     * @return String of the converted time
     * @sample 12:32:57 PM
     *
     * @author Jesse Okoro
     */
    fun convertMilliSecToTime(timeInMill: Long): String {
        val spf = SimpleDateFormat("hh:mm:ss aa")
        return spf.format(timeInMill)
    }

    /**
     * Converts date in milliseconds to a date
     * with format (year:month:day)
     * @param timeInMill in Long
     * @return String of the converted date
     * @sample 2021-10-14
     *
     * @author Jesse Okoro
     */
    fun convertMilliSecToDate(timeInMill: Long): String {
        val spf = SimpleDateFormat("yyyy,MMM dd")
        return spf.format(timeInMill)
    }

    fun getImageUrl(imgCode: String) = "http://openweathermap.org/img/wn/$imgCode@2x.png"

}