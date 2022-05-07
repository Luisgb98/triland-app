package com.luisgb.triland

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*


class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        calculate()
    }

    private fun calculate() {

        val lowWeight: String = getString(R.string.lowWeight)
        val healthy: String = getString(R.string.healthy)
        val overWeight: String = getString(R.string.overWeight)
        val obesity: String = getString(R.string.obesity)

        calculatorButton.setOnClickListener {

            // Comprobamos que el usuario ha introducido los datos
            if (heightET.text.isNotEmpty() && weightET.text.isNotEmpty()) {
                val height = (heightET.text.toString()).toInt()
                val weight = (weightET.text.toString()).toInt()
                val age = (ageET.text.toString()).toInt()

                // Realizamos ambos calculos
                val BMI = calculateBMI(height, weight)
                val BF = calculateBF(height, weight, age)

                BMITVResult.text = "%.2f".format(BMI)
                BMITVResult.visibility = View.VISIBLE

                fatTVResult.text = "%.2f".format(BF) + "%"
                fatTVResult.visibility = View.VISIBLE

                if (BMI < 18.5) {
                    statusTV.text = lowWeight
                } else if (BMI >= 18.5 && BMI < 24.9) {
                    statusTV.text = healthy
                } else if (BMI >= 24.9 && BMI < 29.9) {
                    statusTV.text = overWeight
                } else if (BMI >= 29.9) {
                    statusTV.text = obesity
                }

                BMITVResult.visibility = View.VISIBLE
                statusTV.visibility = View.VISIBLE

                reCalculateButton.visibility = View.VISIBLE
                calculatorButton.visibility = View.GONE

            }

            // Si el usuario no ha introducido bien los datos, salta un error.
            else {
                Toast.makeText(this, (R.string.insertData), Toast.LENGTH_SHORT).show()
            }
        }

        //Reiniciamos todos los datos al pulsar el botón
        reCalculateButton.setOnClickListener {
            ResetEverything()
        }
    }

    // Function to reset all Text and EditText fields.
    private fun ResetEverything() {

        calculatorButton.visibility = View.VISIBLE
        reCalculateButton.visibility = View.GONE

        heightET.text.clear()
        weightET.text.clear()
        ageET.text.clear()
        statusTV.text = " "
        BMITVResult.text = " "
        BMITVResult.visibility = View.GONE
        fatTVResult.text = " "
        fatTVResult.visibility = View.GONE
        genderRG.clearCheck()
    }

    // Function for calculating BMI
    private fun calculateBMI(height: Int, weight: Int): Float {

        val Height_in_metre = height.toFloat() / 100
        val BMI = weight.toFloat() / (Height_in_metre * Height_in_metre)

        return BMI
    }

    private fun calculateBF(height: Int, weight: Int, age: Int): Double {

        val Height_in_metre = height.toFloat() / 100
        val BMI = weight.toFloat() / (Height_in_metre * Height_in_metre)
        var BF = 0.0

        if (genderRG.checkedRadioButtonId == -1) {
            Toast.makeText(this, (R.string.setGender), Toast.LENGTH_SHORT).show()
        } else if (womanRB.isChecked) {
            BF = (1.2 * BMI) + (0.23 * age) - 5.4
        } else if (manRB.isChecked) {
            BF = (1.2 * BMI) + (0.23 * age) - 10.8 - 5.4
        }

        return BF
    }

}