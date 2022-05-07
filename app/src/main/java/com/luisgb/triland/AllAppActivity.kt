package com.luisgb.triland

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_all_app.*

class AllAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_app)

        setupButtons()
    }

    private fun setupButtons() {
        trainingActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, TrainingActivity::class.java))
        }

        professionalActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, ProfessionalActivity::class.java))
        }

        formActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, FormActivity::class.java))
        }

        facilitiesActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, FacilitiesActivity::class.java))
        }

        qrActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, QRActivity::class.java))
        }

        calculatorActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, CalculatorActivity::class.java))
        }

        chronoActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, ChronometerActivity::class.java))
        }

        profileActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, ProfileActivity::class.java))
        }
    }
}