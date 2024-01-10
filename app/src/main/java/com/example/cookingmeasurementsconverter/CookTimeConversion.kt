package com.example.cookingmeasurementsconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CookTimeConversion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_conversion)
    }

    private fun timeCalculator(givenTemp: Int, givenTime: Int, desiredTime: Int): Int {
        val timeDiff = givenTime / desiredTime
        return timeDiff * givenTemp
    }

    private fun tempCalculator(givenTemp: Int, givenTime: Int, desiredTemp: Int): Int {
        val tempDiff = givenTemp / desiredTemp
        return tempDiff * givenTime
    }
}