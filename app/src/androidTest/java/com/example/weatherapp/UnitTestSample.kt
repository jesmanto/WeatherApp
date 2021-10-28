package com.example.weatherapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import org.junit.Test

const val FAKE_STRING = "HELLO WORLD"
class UnitTestSample {
    val context = ApplicationProvider.getApplicationContext<Context>()

}