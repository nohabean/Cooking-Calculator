package com.example.cookingmeasurementsconverter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.apache.commons.math3.fraction.Fraction
import android.view.inputmethod.InputMethodManager
import org.w3c.dom.Text
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val measurementCalc = findViewById<Button>(R.id.measurementConverter)
        val timeCalc = findViewById<Button>(R.id.timeConverter)

        measurementCalc.setOnClickListener {
            val intent = Intent(this@MainActivity, MeasurementConversion::class.java)
            startActivity(intent)
        }

        timeCalc.setOnClickListener {
            val intent = Intent(this@MainActivity, CookTimeConversion::class.java)
            startActivity(intent)
        }
    }
}
