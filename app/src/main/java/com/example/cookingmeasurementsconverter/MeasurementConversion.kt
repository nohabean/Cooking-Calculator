package com.example.cookingmeasurementsconverter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.apache.commons.math3.fraction.Fraction
import android.view.inputmethod.InputMethodManager


class MeasurementConversion : AppCompatActivity() {
    val availableCupSizes = arrayOf(1.0, 0.5, 0.33, 0.25)
    val availableTablespoonSizes = arrayOf(1.0, 0.5)
    val availableTeaspoonSizes = arrayOf(1.0, 0.5, 0.25)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.measurement_conversion)

        setUpSwitches()

        val calculateButton = findViewById<Button>(R.id.calculate)
        calculateButton.setOnClickListener {
            // Hide the keyboard when the "Calculate" button is clicked
            hideKeyboard()

            if (findViewById<Switch>(R.id.cupSwitch).isChecked) {
                // Calculate cup conversion if the switch is checked
                calculateCups()
            }
            if (findViewById<Switch>(R.id.tablespoonSwitch).isChecked) {
                // Calculate cup conversion if the switch is checked
                calculateTablespoons()
            }
            if (findViewById<Switch>(R.id.teaspoonSwitch).isChecked) {
                calculateTeaspoons()
            }
        }

        val resetButton = findViewById<Button>(R.id.reset)
        resetButton.setOnClickListener {
            reset()
        }
    }

    private fun changeSwitchState(switch: Switch, wholeEditText: EditText, fraction1EditText: EditText,
                                  fraction2EditText: EditText, emptyText: TextView) {
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Enable Switch
                wholeEditText.isEnabled = true
                fraction1EditText.isEnabled = true
                fraction2EditText.isEnabled = true
            } else {
                // Disable Switch
                wholeEditText.isEnabled = false
                fraction1EditText.isEnabled = false
                fraction2EditText.isEnabled = false
                wholeEditText.text.clear()
                fraction1EditText.text.clear()
                fraction2EditText.text.clear()
                emptyText.text = ""
            }
        }
    }

    private fun setUpSwitches() {
        // set cup switch
        changeSwitchState(
            findViewById(R.id.cupSwitch),
            findViewById(R.id.convertCupInputWhole),
            findViewById(R.id.convertCupInputFraction1),
            findViewById(R.id.convertCupInputFraction2),
            findViewById(R.id.cupCalculation)
        )

        // set tablespoon switch
        changeSwitchState(
            findViewById(R.id.tablespoonSwitch),
            findViewById(R.id.convertTablespoonInputWhole),
            findViewById(R.id.convertTablespoonInputFraction1),
            findViewById(R.id.convertTablespoonInputFraction2),
            findViewById(R.id.tablespoonCalculation)
        )

        // set teaspoon switch
        changeSwitchState(
            findViewById(R.id.teaspoonSwitch),
            findViewById(R.id.convertTeaspoonInputWhole),
            findViewById(R.id.convertTeaspoonInputFraction1),
            findViewById(R.id.convertTeaspoonInputFraction2),
            findViewById(R.id.teaspoonCalculation)
        )
    }

    private fun calculateCups() {
        val cupWhole = findViewById<EditText>(R.id.convertCupInputWhole).text.toString().toDoubleOrNull()
        val cupFraction1 = findViewById<EditText>(R.id.convertCupInputFraction1).text.toString().toDoubleOrNull()
        val cupFraction2 = findViewById<EditText>(R.id.convertCupInputFraction2).text.toString().toDoubleOrNull()
        val divisor = findViewById<EditText>(R.id.divider).text.toString().toDoubleOrNull()
        val cupCalculation = findViewById<TextView>(R.id.cupCalculation)

        if (divisor != null && divisor != 0.0) {
            if (cupWhole != null && cupWhole != 0.0) {
                if (cupFraction2 != null && cupFraction2 != 0.0) {
                    if (cupFraction1 != null) {
                        val cupTotal = cupWhole + (cupFraction1 / cupFraction2)
                        val cupResult: Double = cupTotal / divisor

                        displayFraction(cupResult, cupCalculation)
                    } else {
                        showError("Enter a numerator", cupCalculation)
                    }
                } else {
                    if (cupFraction1 != null && cupFraction1 != 0.0) {
                        showError("Cannot Divide By 0", cupCalculation)
                    } else {
                        val cupResult: Double = cupWhole / divisor

                        displayFraction(cupResult, cupCalculation)
                    }
                }
            } else {
                if (cupFraction2 != null && cupFraction2 != 0.0) {
                    if (cupFraction1 != null) {
                        val cupTotal = cupFraction1 / cupFraction2
                        val cupResult: Double = cupTotal / divisor

                        displayFraction(cupResult, cupCalculation)
                    } else {
                        showError("Enter a numerator", cupCalculation)
                    }
                } else {
                    showError("Cannot Divide By 0", cupCalculation)
                }
            }
        } else {
            showError("Cannot Divide By 0", cupCalculation)
        }
    }

    private fun calculateTablespoons() {
        val tablespoonWhole = findViewById<EditText>(R.id.convertTablespoonInputWhole).text.toString().toDoubleOrNull()
        val tablespoonFraction1 = findViewById<EditText>(R.id.convertTablespoonInputFraction1).text.toString().toDoubleOrNull()
        val tablespoonFraction2 = findViewById<EditText>(R.id.convertTablespoonInputFraction2).text.toString().toDoubleOrNull()
        val divisor = findViewById<EditText>(R.id.divider).text.toString().toDoubleOrNull()
        val tablespoonCalculation = findViewById<TextView>(R.id.tablespoonCalculation)

        if (divisor != null && divisor != 0.0) {
            if (tablespoonWhole != null && tablespoonWhole != 0.0) {
                if (tablespoonFraction2 != null && tablespoonFraction2 != 0.0) {
                    if (tablespoonFraction1 != null) {
                        val tablespoonTotal = tablespoonWhole + (tablespoonFraction1 / tablespoonFraction2)
                        val tablespoonResult: Double = tablespoonTotal / divisor

                        displayFraction(tablespoonResult, tablespoonCalculation)
                    } else {
                        showError("Enter a numerator", tablespoonCalculation)
                    }
                } else {
                    if (tablespoonFraction1 != null && tablespoonFraction1 != 0.0) {
                        showError("Cannot Divide By 0", tablespoonCalculation)
                    } else {
                        val tablespoonResult: Double = tablespoonWhole / divisor

                        displayFraction(tablespoonResult, tablespoonCalculation)
                    }
                }
            } else {
                if (tablespoonFraction2 != null && tablespoonFraction2 != 0.0) {
                    if (tablespoonFraction1 != null) {
                        val tablespoonTotal = tablespoonFraction1 / tablespoonFraction2
                        val tablespoonResult: Double = tablespoonTotal / divisor

                        displayFraction(tablespoonResult, tablespoonCalculation)
                    } else {
                        showError("Enter a numerator", tablespoonCalculation)
                    }
                } else {
                    showError("Cannot Divide By 0", tablespoonCalculation)
                }
            }
        } else {
            showError("Cannot Divide By 0", tablespoonCalculation)
        }
    }

    private fun calculateTeaspoons() {
        val teaspoonWhole = findViewById<EditText>(R.id.convertTeaspoonInputWhole).text.toString().toDoubleOrNull()
        val teaspoonFraction1 = findViewById<EditText>(R.id.convertTeaspoonInputFraction1).text.toString().toDoubleOrNull()
        val teaspoonFraction2 = findViewById<EditText>(R.id.convertTeaspoonInputFraction2).text.toString().toDoubleOrNull()
        val divisor = findViewById<EditText>(R.id.divider).text.toString().toDoubleOrNull()
        val teaspoonCalculation = findViewById<TextView>(R.id.teaspoonCalculation)

        if (divisor != null && divisor != 0.0) {
            if (teaspoonWhole != null && teaspoonWhole != 0.0) {
                if (teaspoonFraction2 != null && teaspoonFraction2 != 0.0) {
                    if (teaspoonFraction1 != null) {
                        val teaspoonTotal = teaspoonWhole + (teaspoonFraction1 / teaspoonFraction2)
                        val teaspoonResult: Double = teaspoonTotal / divisor

                        displayFraction(teaspoonResult, teaspoonCalculation)
                    } else {
                        showError("Enter a numerator", teaspoonCalculation)
                    }
                } else {
                    if (teaspoonFraction1 != null && teaspoonFraction1 != 0.0) {
                        showError("Cannot Divide By 0", teaspoonCalculation)
                    } else {
                        val teaspoonResult: Double = teaspoonWhole / divisor

                        displayFraction(teaspoonResult, teaspoonCalculation)
                    }
                }
            } else {
                if (teaspoonFraction2 != null && teaspoonFraction2 != 0.0) {
                    if (teaspoonFraction1 != null) {
                        val teaspoonTotal = teaspoonFraction1 / teaspoonFraction2
                        val teaspoonResult: Double = teaspoonTotal / divisor

                        displayFraction(teaspoonResult, teaspoonCalculation)
                    } else {
                        showError("Enter a numerator", teaspoonCalculation)
                    }
                } else {
                    showError("Cannot Divide By 0", teaspoonCalculation)
                }
            }
        } else {
            showError("Cannot Divide By 0", teaspoonCalculation)
        }
    }

    private fun displayFraction(result: Double, textView: TextView) {
        val fraction = Fraction(result)
        val numerator = fraction.numerator
        val denominator = fraction.denominator

        if (denominator == 1) {
            textView.text = "$numerator"
        } else {
            if (numerator >= denominator) {
                val wholePart = numerator / denominator
                val remainder = numerator % denominator

                if (remainder == 0) {
                    textView.text = wholePart.toString()
                } else {
                    textView.text = "$wholePart ${remainder}/${denominator}"
                }
            } else {
                textView.text = "$numerator / $denominator"
            }
        }
    }

    private fun showError(message: String, emptyText: TextView) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        emptyText.text = ""
    }

    private fun reset() {
        findViewById<Switch>(R.id.cupSwitch).isChecked = false
        findViewById<Switch>(R.id.tablespoonSwitch).isChecked = false
        findViewById<Switch>(R.id.teaspoonSwitch).isChecked = false
        findViewById<EditText>(R.id.divider).text.clear()
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val input = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            input.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
