package com.example.cookingmeasurementsconverter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class CookTimeConversion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_conversion)

        val originalTempInput = findViewById<EditText>(R.id.originalTempEditText)
        val originalTempSpinner = findViewById<Spinner>(R.id.tempUnits)

        val originalTimeInput = findViewById<EditText>(R.id.originalTimeEditText)
        val originalTimeSpinner = findViewById<Spinner>(R.id.timeUnits)

        val modifyTimeButton = findViewById<Button>(R.id.changeTime)
        val modifyTempButton = findViewById<Button>(R.id.changeTemp)

        val desiredChangeTitle = findViewById<TextView>(R.id.desiredConversion)
        val desiredInput = findViewById<EditText>(R.id.desiredConversionEditText)
        val desiredUnits = findViewById<TextView>(R.id.desiredConversionUnits)

        val calculateButton = findViewById<Button>(R.id.calculateConversion)

        val resultTitle = findViewById<TextView>(R.id.conversionResult)
        val result = findViewById<TextView>(R.id.result)
        val resultUnits = findViewById<TextView>(R.id.resultUnits)

        val backButton = findViewById<Button>(R.id.backButton)
        val resetButton = findViewById<Button>(R.id.resetButton)

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