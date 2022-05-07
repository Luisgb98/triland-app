package com.luisgb.triland

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_register.*

class FormActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        send();
    }

    private fun send() {
        sendButton.setOnClickListener{
            db.collection("form").document(emailET.text.toString()).set(
                hashMapOf("email" to emailET.text.toString(),
                    "name" to nameET.text.toString(),
                    "message" to messageET.text.toString())
            )
        }
    }
}