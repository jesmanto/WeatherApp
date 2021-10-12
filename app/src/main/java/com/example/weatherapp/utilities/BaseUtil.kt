package com.example.weatherapp.utilities

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.SuperscriptSpan
import java.text.SimpleDateFormat

object BaseUtil {
    /**
     * Converts temperature to string and builds the string to
     * accommodate degree celsius
     */
    fun covertTempToString(temp: Double): SpannableStringBuilder {
        val superscriptSpan = SuperscriptSpan()
        val builder = SpannableStringBuilder("${temp.toInt() - 273.0} oC")
        builder.setSpan(
            superscriptSpan,
            "${temp.toInt() - 273.0} oC".indexOf("o"),
            "${temp.toInt() - 273.0} oC".indexOf("o") + "o".length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return builder
    }

    fun convertMilliSecToTime(timeInMill: Long): String {
        val spf = SimpleDateFormat("hh:mm:ss aa")
        return spf.format(timeInMill)
    }

    fun convertMilliSecToDate(timeInMill: Long): String {
        val spf = SimpleDateFormat("yyyy,MMM dd")
        return spf.format(timeInMill)
    }
}