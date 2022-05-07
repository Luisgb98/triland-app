package com.luisgb.triland

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davemorrissey.labs.subscaleview.ImageSource
import kotlinx.android.synthetic.main.activity_training.*

class TrainingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)

        trainingIV1.setImage(ImageSource.resource(R.drawable.training1))
        trainingIV2.setImage(ImageSource.resource(R.drawable.training2))

    }
}