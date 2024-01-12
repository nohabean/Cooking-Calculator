package com.example.cookingmeasurementsconverter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import kotlin.math.sqrt

class CookTimeConversion : AppCompatActivity() {
    private var isModifyingTime: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_conversion)

        val originalTempInput = findViewById<EditText>(R.id.originalTempEditText)
        val originalTempSpinner = findViewById<Spinner>(R.id.tempUnits)

        val originalTimeInput = findViewById<EditText>(R.id.originalTimeEditText)
        val originalTimeSpinner = findViewById<Spinner>(R.id.timeUnits)

        val modifyTimeButton = findViewById<Button>(R.id.changeTime)
        val modifyTempButton = findViewById<Button>(R.id.changeTemp)

        var desiredChangeTitle = findViewById<TextView>(R.id.desiredConversion)
        val desiredInput = findViewById<EditText>(R.id.desiredConversionEditText)
        var desiredUnits = findViewById<TextView>(R.id.desiredConversionUnits)
        desiredInput.visibility = View.INVISIBLE

        val calculateButton = findViewById<Button>(R.id.calculateConversion)
        calculateButton.visibility = View.INVISIBLE

        var resultTitle = findViewById<TextView>(R.id.conversionResult)
        var result = findViewById<TextView>(R.id.result)
        var resultUnits = findViewById<TextView>(R.id.resultUnits)
        result.visibility = View.INVISIBLE

        modifyTimeButton.setOnClickListener {
            if (originalTimeInput.text.isNullOrEmpty() || originalTempInput.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter temperature and cook time", Toast.LENGTH_LONG).show()
            }
            else {
                hideKeyboard()
                desiredInput.text = null
                resultTitle.text = null
                result.text = null
                resultUnits.text = null
                result.visibility = View.INVISIBLE

                isModifyingTime = true
                desiredChangeTitle.text = "Desired Cook Time"
                desiredInput.visibility = View.VISIBLE
                desiredUnits.visibility = View.VISIBLE
                calculateButton.visibility = View.VISIBLE
                desiredUnits.text = originalTimeSpinner.selectedItem.toString()
            }
        }

        modifyTempButton.setOnClickListener {
            if (originalTimeInput.text.isNullOrEmpty() || originalTempInput.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter temperature and cook time", Toast.LENGTH_LONG).show()
            }
            else {
                hideKeyboard()
                desiredInput.text = null
                resultTitle.text = null
                result.text = null
                resultUnits.text = null
                result.visibility = View.INVISIBLE

                isModifyingTime = false
                desiredChangeTitle.text = "Desired Cook Temperature"
                desiredInput.visibility = View.VISIBLE
                desiredUnits.visibility = View.VISIBLE
                calculateButton.visibility = View.VISIBLE
                desiredUnits.text = originalTempSpinner.selectedItem.toString()
            }
        }

        calculateButton.setOnClickListener {
            if (desiredInput.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter a desired value", Toast.LENGTH_LONG).show()
            }
            else {
                hideKeyboard()
                result.visibility = View.VISIBLE
                if (isModifyingTime == true) {
                    resultTitle.text = "NEW COOK TEMPERATURE"
                    resultUnits.text = originalTempSpinner.selectedItem.toString()
                    result.text = modifyCookTime(originalTempInput, originalTimeInput, desiredInput)
                } else if (isModifyingTime == false) {
                    resultTitle.text = "NEW COOK TIME"
                    resultUnits.text = originalTimeSpinner.selectedItem.toString()
                    result.text = modifyCookTemp(originalTempInput, originalTimeInput, desiredInput)
                }
            }
        }

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener {
            reset()
        }
    }

    private fun modifyCookTime(givenTemp: EditText, givenTime: EditText, desiredTime: EditText): String {
        val timeDiff = givenTime.text.toString().toDouble() / desiredTime.text.toString().toDouble()
        val result = timeDiff * givenTemp.text.toString().toDouble()
        return result.toInt().toString()
    }

    private fun modifyCookTemp(givenTemp: EditText, givenTime: EditText, desiredTemp: EditText): String {
        val tempDiff = givenTemp.text.toString().toDouble() / desiredTemp.text.toString().toDouble()
        val result = tempDiff * givenTime.text.toString().toDouble()
        return result.toInt().toString()
    }

    private fun reset() {
        val originalTempInput = findViewById<EditText>(R.id.originalTempEditText)
        val originalTimeInput = findViewById<EditText>(R.id.originalTimeEditText)

        val desiredChangeTitle = findViewById<TextView>(R.id.desiredConversion)
        val desiredInput = findViewById<EditText>(R.id.desiredConversionEditText)
        val desiredUnits = findViewById<TextView>(R.id.desiredConversionUnits)

        val calculateButton = findViewById<Button>(R.id.calculateConversion)

        val resultTitle = findViewById<TextView>(R.id.conversionResult)
        val result = findViewById<TextView>(R.id.result)
        val resultUnits = findViewById<TextView>(R.id.resultUnits)

        originalTempInput.text = null
        originalTimeInput.text = null

        desiredChangeTitle.text = null
        desiredInput.text = null
        desiredUnits.text = null

        resultTitle.text = null
        result.text = null
        resultUnits.text = null

        desiredInput.visibility = View.INVISIBLE
        calculateButton.visibility = View.INVISIBLE
        result.visibility = View.INVISIBLE
    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}