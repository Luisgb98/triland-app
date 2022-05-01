package com.luisgb.triland

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_all_app.*

class AllAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_app)

        trainingActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, TrainingActivity::class.java))
        }

        dailyActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, DailyActivity::class.java))
        }

        membershipActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, MembershipActivity::class.java))
        }

        facilitiesActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, FacilitiesActivity::class.java))
        }

        qrActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, QRActivity::class.java))
        }

        shopActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, ShopActivity::class.java))
        }

        rankingActButton.setOnClickListener {

            startActivity(Intent(this@AllAppActivity, RankingActivity::class.java))
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