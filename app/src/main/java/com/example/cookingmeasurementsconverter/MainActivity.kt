package com.example.cookingmeasurementsconverter

import android.content.Context
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


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setCupSwitch()
        setTablespoonSwitch()
        setTeaspoonSwitch()

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

    private fun setCupSwitch() {
        val cupWholeEditText = findViewById<EditText>(R.id.convertCupInputWhole)
        val cupFraction1EditText = findViewById<EditText>(R.id.convertCupInputFraction1)
        val cupFraction2EditText = findViewById<EditText>(R.id.convertCupInputFraction2)

        val cupSwitch = findViewById<Switch>(R.id.cupSwitch)
        cupSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Enable or disable the EditText views based on the switch state
            if (isChecked) {
                cupWholeEditText.isEnabled = true
                cupFraction1EditText.isEnabled = true
                cupFraction2EditText.isEnabled = true
            } else {
                cupWholeEditText.isEnabled = false
                cupFraction1EditText.isEnabled = false
                cupFraction2EditText.isEnabled = false
                cupWholeEditText.text.clear()
                cupFraction1EditText.text.clear()
                cupFraction2EditText.text.clear()
                findViewById<TextView>(R.id.cupCalculation).text = ""
            }
        }
    }

    private fun setTablespoonSwitch() {
        val tablespoonWholeEditText = findViewById<EditText>(R.id.convertTablespoonInputWhole)
        val tablespoonFraction1EditText = findViewById<EditText>(R.id.convertTablespoonInputFraction1)
        val tablespoonFraction2EditText = findViewById<EditText>(R.id.convertTablespoonInputFraction2)

        val tablespoonSwitch = findViewById<Switch>(R.id.tablespoonSwitch)
        tablespoonSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Enable or disable the EditText views based on the switch state
            if (isChecked) {
                tablespoonWholeEditText.isEnabled = true
                tablespoonFraction1EditText.isEnabled = true
                tablespoonFraction2EditText.isEnabled = true
            } else {
                tablespoonWholeEditText.isEnabled = false
                tablespoonFraction1EditText.isEnabled = false
                tablespoonFraction2EditText.isEnabled = false
                tablespoonWholeEditText.text.clear()
                tablespoonFraction1EditText.text.clear()
                tablespoonFraction2EditText.text.clear()
                findViewById<TextView>(R.id.tablespoonCalculation).text = ""
            }
        }
    }

    private fun setTeaspoonSwitch() {
        val teaspoonWholeEditText = findViewById<EditText>(R.id.convertTeaspoonInputWhole)
        val teaspoonFraction1EditText = findViewById<EditText>(R.id.convertTeaspoonInputFraction1)
        val teaspoonFraction2EditText = findViewById<EditText>(R.id.convertTeaspoonInputFraction2)

        val teaspoonSwitch = findViewById<Switch>(R.id.teaspoonSwitch)
        teaspoonSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Enable or disable the EditText views based on the switch state
            if (isChecked) {
                teaspoonWholeEditText.isEnabled = true
                teaspoonFraction1EditText.isEnabled = true
                teaspoonFraction2EditText.isEnabled = true
            } else {
                teaspoonWholeEditText.isEnabled = false
                teaspoonFraction1EditText.isEnabled = false
                teaspoonFraction2EditText.isEnabled = false
                teaspoonWholeEditText.text.clear()
                teaspoonFraction1EditText.text.clear()
                teaspoonFraction2EditText.text.clear()
                findViewById<TextView>(R.id.teaspoonCalculation).text = ""
            }
        }
    }

    private fun calculateCups() {
        val cupWhole = findViewById<EditText>(R.id.convertCupInputWhole).text.toString().toDoubleOrNull()
        val cupFraction1 = findViewById<EditText>(R.id.convertCupInputFraction1).text.toString().toDoubleOrNull()
        val cupFraction2 = findViewById<EditText>(R.id.convertCupInputFraction2).text.toString().toDoubleOrNull()
        val divisor = findViewById<EditText>(R.id.divider).text.toString().toDoubleOrNull()

        if (divisor != null && divisor != 0.0) {
            if (cupWhole != null && cupWhole != 0.0) {
                if (cupFraction2 != null && cupFraction2 != 0.0) {
                    if (cupFraction1 != null) {
                        val cupTotal = cupWhole + (cupFraction1 / cupFraction2)
                        val cupResult: Double = cupTotal / divisor

                        val fraction = Fraction(cupResult)
                        val numerator = fraction.numerator
                        val denominator = fraction.denominator

                        if (denominator == 1) {
                            findViewById<TextView>(R.id.cupCalculation).text = "$numerator"
                        } else {
                            if (numerator >= denominator) {
                                val wholePart = numerator / denominator
                                val remainder = numerator % denominator

                                if (remainder == 0) {
                                    findViewById<TextView>(R.id.cupCalculation).text = wholePart.toString()
                                } else {
                                    findViewById<TextView>(R.id.cupCalculation).text = "$wholePart ${remainder}/${denominator}"
                                }
                            } else {
                                findViewById<TextView>(R.id.cupCalculation).text = "$numerator / $denominator"
                            }
                        }
                    } else {
                        Toast.makeText(this, "Enter a numerator", Toast.LENGTH_LONG).show()
                        findViewById<TextView>(R.id.cupCalculation).text = ""
                    }
                } else {
                    if (cupFraction1 != null && cupFraction1 != 0.0) {
                        Toast.makeText(this, "Cannot Divide By 0", Toast.LENGTH_LONG).show()
                        findViewById<TextView>(R.id.cupCalculation).text = ""
                    } else {
                        val cupResult: Double = cupWhole / divisor

                        val fraction = Fraction(cupResult)
                        val numerator = fraction.numerator
                        val denominator = fraction.denominator

                        if (denominator == 1) {
                            findViewById<TextView>(R.id.cupCalculation).text = "$numerator"
                        } else {
                            if (numerator >= denominator) {
                                val wholePart = numerator / denominator
                                val remainder = numerator % denominator

                                if (remainder == 0) {
                                    findViewById<TextView>(R.id.cupCalculation).text = wholePart.toString()
                                } else {
                                    findViewById<TextView>(R.id.cupCalculation).text = "$wholePart ${remainder}/${denominator}"
                                }
                            } else {
                                findViewById<TextView>(R.id.cupCalculation).text = "$numerator / $denominator"
                            }
                        }
                    }
                }
            } else {
                if (cupFraction2 != null && cupFraction2 != 0.0) {
                    if (cupFraction1 != null) {
                        val cupTotal = cupFraction1 / cupFraction2
                        val cupResult: Double = cupTotal / divisor

                        val fraction = Fraction(cupResult)
                        val numerator = fraction.numerator
                        val denominator = fraction.denominator

                        if (denominator == 1) {
                            findViewById<TextView>(R.id.cupCalculation).text = "$numerator"
                        } else {
                            if (numerator >= denominator) {
                                val wholePart = numerator / denominator
                                val remainder = numerator % denominator

                                if (remainder == 0) {
                                    findViewById<TextView>(R.id.cupCalculation).text = wholePart.toString()
                                } else {
                                    findViewById<TextView>(R.id.cupCalculation).text = "$wholePart ${remainder}/${denominator}"
                                }
                            } else {
                                findViewById<TextView>(R.id.cupCalculation).text = "$numerator / $denominator"
                            }
                        }
                    } else {
                        Toast.makeText(this, "Enter a numerator", Toast.LENGTH_LONG).show()
                        findViewById<TextView>(R.id.cupCalculation).text = ""
                    }
                } else {
                    Toast.makeText(this, "Cannot Divide By 0", Toast.LENGTH_LONG).show()
                    findViewById<TextView>(R.id.cupCalculation).text = ""
                }
            }
        } else {
            Toast.makeText(this, "Cannot Divide By 0", Toast.LENGTH_LONG).show()
            findViewById<TextView>(R.id.cupCalculation).text = ""
        }
    }

    private fun calculateTablespoons() {
        val tablespoonWhole = findViewById<EditText>(R.id.convertTablespoonInputWhole).text.toString().toDoubleOrNull()
        val tablespoonFraction1 = findViewById<EditText>(R.id.convertTablespoonInputFraction1).text.toString().toDoubleOrNull()
        val tablespoonFraction2 = findViewById<EditText>(R.id.convertTablespoonInputFraction2).text.toString().toDoubleOrNull()
        val divisor = findViewById<EditText>(R.id.divider).text.toString().toDoubleOrNull()

        if (divisor != null && divisor != 0.0) {
            if (tablespoonWhole != null && tablespoonWhole != 0.0) {
                if (tablespoonFraction2 != null && tablespoonFraction2 != 0.0) {
                    if (tablespoonFraction1 != null) {
                        val tablespoonTotal = tablespoonWhole + (tablespoonFraction1 / tablespoonFraction2)
                        val tablespoonResult: Double = tablespoonTotal / divisor

                        val fraction = Fraction(tablespoonResult)
                        val numerator = fraction.numerator
                        val denominator = fraction.denominator

                        if (denominator == 1) {
                            findViewById<TextView>(R.id.tablespoonCalculation).text = "$numerator"
                        } else {
                            if (numerator >= denominator) {
                                val wholePart = numerator / denominator
                                val remainder = numerator % denominator

                                if (remainder == 0) {
                                    findViewById<TextView>(R.id.tablespoonCalculation).text = wholePart.toString()
                                } else {
                                    findViewById<TextView>(R.id.tablespoonCalculation).text = "$wholePart ${remainder}/${denominator}"
                                }
                            } else {
                                findViewById<TextView>(R.id.tablespoonCalculation).text = "$numerator / $denominator"
                            }
                        }
                    } else {
                        Toast.makeText(this, "Enter a numerator", Toast.LENGTH_LONG).show()
                        findViewById<TextView>(R.id.tablespoonCalculation).text = ""
                    }
                } else {
                    if (tablespoonFraction1 != null && tablespoonFraction1 != 0.0) {
                        Toast.makeText(this, "Cannot Divide By 0", Toast.LENGTH_LONG).show()
                        findViewById<TextView>(R.id.tablespoonCalculation).text = ""
                    } else {
                        val tablespoonResult: Double = tablespoonWhole / divisor

                        val fraction = Fraction(tablespoonResult)
                        val numerator = fraction.numerator
                        val denominator = fraction.denominator

                        if (denominator == 1) {
                            findViewById<TextView>(R.id.tablespoonCalculation).text = "$numerator"
                        } else {
                            if (numerator >= denominator) {
                                val wholePart = numerator / denominator
                                val remainder = numerator % denominator

                                if (remainder == 0) {
                                    findViewById<TextView>(R.id.tablespoonCalculation).text = wholePart.toString()
                                } else {
                                    findViewById<TextView>(R.id.tablespoonCalculation).text = "$wholePart ${remainder}/${denominator}"
                                }
                            } else {
                                findViewById<TextView>(R.id.tablespoonCalculation).text = "$numerator / $denominator"
                            }
                        }
                    }
                }
            } else {
                if (tablespoonFraction2 != null && tablespoonFraction2 != 0.0) {
                    if (tablespoonFraction1 != null) {
                        val tablespoonTotal = tablespoonFraction1 / tablespoonFraction2
                        val tablespoonResult: Double = tablespoonTotal / divisor

                        val fraction = Fraction(tablespoonResult)
                        val numerator = fraction.numerator
                        val denominator = fraction.denominator

                        if (denominator == 1) {
                            findViewById<TextView>(R.id.tablespoonCalculation).text = "$numerator"
                        } else {
                            if (numerator >= denominator) {
                                val wholePart = numerator / denominator
                                val remainder = numerator % denominator

                                if (remainder == 0) {
                                    findViewById<TextView>(R.id.tablespoonCalculation).text = wholePart.toString()
                                } else {
                                    findViewById<TextView>(R.id.tablespoonCalculation).text = "$wholePart ${remainder}/${denominator}"
                                }
                            } else {
                                findViewById<TextView>(R.id.tablespoonCalculation).text = "$numerator / $denominator"
                            }
                        }
                    } else {
                        Toast.makeText(this, "Enter a numerator", Toast.LENGTH_LONG).show()
                        findViewById<TextView>(R.id.tablespoonCalculation).text = ""
                    }
                } else {
                    Toast.makeText(this, "Cannot Divide By 0", Toast.LENGTH_LONG).show()
                    findViewById<TextView>(R.id.tablespoonCalculation).text = ""
                }
            }
        } else {
            Toast.makeText(this, "Cannot Divide By 0", Toast.LENGTH_LONG).show()
            findViewById<TextView>(R.id.tablespoonCalculation).text = ""
        }
    }

    private fun calculateTeaspoons() {
        val teaspoonWhole = findViewById<EditText>(R.id.convertTeaspoonInputWhole).text.toString().toDoubleOrNull()
        val teaspoonFraction1 = findViewById<EditText>(R.id.convertTeaspoonInputFraction1).text.toString().toDoubleOrNull()
        val teaspoonFraction2 = findViewById<EditText>(R.id.convertTeaspoonInputFraction2).text.toString().toDoubleOrNull()
        val divisor = findViewById<EditText>(R.id.divider).text.toString().toDoubleOrNull()

        if (divisor != null && divisor != 0.0) {
            if (teaspoonWhole != null && teaspoonWhole != 0.0) {
                if (teaspoonFraction2 != null && teaspoonFraction2 != 0.0) {
                    if (teaspoonFraction1 != null) {
                        val teaspoonTotal = teaspoonWhole + (teaspoonFraction1 / teaspoonFraction2)
                        val teaspoonResult: Double = teaspoonTotal / divisor

                        val fraction = Fraction(teaspoonResult)
                        val numerator = fraction.numerator
                        val denominator = fraction.denominator

                        if (denominator == 1) {
                            findViewById<TextView>(R.id.teaspoonCalculation).text = "$numerator"
                        } else {
                            if (numerator >= denominator) {
                                val wholePart = numerator / denominator
                                val remainder = numerator % denominator

                                if (remainder == 0) {
                                    findViewById<TextView>(R.id.teaspoonCalculation).text = wholePart.toString()
                                } else {
                                    findViewById<TextView>(R.id.teaspoonCalculation).text = "$wholePart ${remainder}/${denominator}"
                                }
                            } else {
                                findViewById<TextView>(R.id.teaspoonCalculation).text = "$numerator / $denominator"
                            }
                        }
                    } else {
                        Toast.makeText(this, "Enter a numerator", Toast.LENGTH_LONG).show()
                        findViewById<TextView>(R.id.teaspoonCalculation).text = ""
                    }
                } else {
                    if (teaspoonFraction1 != null && teaspoonFraction1 != 0.0) {
                        Toast.makeText(this, "Cannot Divide By 0", Toast.LENGTH_LONG).show()
                        findViewById<TextView>(R.id.teaspoonCalculation).text = ""
                    } else {
                        val teaspoonResult: Double = teaspoonWhole / divisor

                        val fraction = Fraction(teaspoonResult)
                        val numerator = fraction.numerator
                        val denominator = fraction.denominator

                        if (denominator == 1) {
                            findViewById<TextView>(R.id.teaspoonCalculation).text = "$numerator"
                        } else {
                            if (numerator >= denominator) {
                                val wholePart = numerator / denominator
                                val remainder = numerator % denominator

                                if (remainder == 0) {
                                    findViewById<TextView>(R.id.teaspoonCalculation).text = wholePart.toString()
                                } else {
                                    findViewById<TextView>(R.id.teaspoonCalculation).text = "$wholePart ${remainder}/${denominator}"
                                }
                            } else {
                                findViewById<TextView>(R.id.teaspoonCalculation).text = "$numerator / $denominator"
                            }
                        }
                    }
                }
            } else {
                if (teaspoonFraction2 != null && teaspoonFraction2 != 0.0) {
                    if (teaspoonFraction1 != null) {
                        val teaspoonTotal = teaspoonFraction1 / teaspoonFraction2
                        val teaspoonResult: Double = teaspoonTotal / divisor

                        val fraction = Fraction(teaspoonResult)
                        val numerator = fraction.numerator
                        val denominator = fraction.denominator

                        if (denominator == 1) {
                            findViewById<TextView>(R.id.teaspoonCalculation).text = "$numerator"
                        } else {
                            if (numerator >= denominator) {
                                val wholePart = numerator / denominator
                                val remainder = numerator % denominator

                                if (remainder == 0) {
                                    findViewById<TextView>(R.id.teaspoonCalculation).text = wholePart.toString()
                                } else {
                                    findViewById<TextView>(R.id.teaspoonCalculation).text = "$wholePart ${remainder}/${denominator}"
                                }
                            } else {
                                findViewById<TextView>(R.id.teaspoonCalculation).text = "$numerator / $denominator"
                            }
                        }
                    } else {
                        Toast.makeText(this, "Enter a numerator", Toast.LENGTH_LONG).show()
                        findViewById<TextView>(R.id.teaspoonCalculation).text = ""
                    }
                } else {
                    Toast.makeText(this, "Cannot Divide By 0", Toast.LENGTH_LONG).show()
                    findViewById<TextView>(R.id.teaspoonCalculation).text = ""
                }
            }
        } else {
            Toast.makeText(this, "Cannot Divide By 0", Toast.LENGTH_LONG).show()
            findViewById<TextView>(R.id.teaspoonCalculation).text = ""
        }
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
