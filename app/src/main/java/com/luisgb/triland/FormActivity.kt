package com.luisgb.triland

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        send()
    }

    private fun send() {
        sendButton.setOnClickListener {
            db.collection("form").document(emailET.text.toString()).set(
                hashMapOf(
                    "email" to emailET.text.toString(),
                    "name" to nameET.text.toString(),
                    "message" to messageET.text.toString()
                )
            )
            reset()
        }
    }

    private fun reset() {
        emailET.text.clear()
        nameET.text.clear()
        messageET.text.clear()
    }
}