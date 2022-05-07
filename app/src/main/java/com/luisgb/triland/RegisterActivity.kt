package com.luisgb.triland

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        login()
        setup()
    }

    private fun setup() {

        registerButton.setOnClickListener {
            if (register_email.text.isNotEmpty() && register_pass.text.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    register_email.text.toString(),
                    register_pass.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            this@RegisterActivity,
                            (R.string.registerSuccessful),
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this, AllAppActivity::class.java))
                    } else {
                        showAlert()
                    }
                }
            }

            //Guarda los datos en la tabla users
            db.collection("users").document(register_email.text.toString()).set(
                hashMapOf(
                    "name" to register_name.text.toString(),
                    "surname" to register_surname.text.toString()
                )
            )

        }

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.error)
        builder.setMessage(R.string.enterDataCorrect)
        builder.setPositiveButton(R.string.accept, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun login() {
        //Añadimos funcionalidad al texto/botón "Inicia sesión"
        iniciasesionTextView.setOnClickListener {
            //Para no crear infinitas ventanas si se usa varias veces
            onBackPressed()
            //startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

}