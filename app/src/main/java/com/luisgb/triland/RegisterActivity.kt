package com.luisgb.triland

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Añadimos funcionalidad al texto/botón "Inicia sesión"
        iniciasesionTextView.setOnClickListener {
            //Para no crear infinitas ventanas si se usa varias veces
            onBackPressed()
            //startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

        //setup
        setup()
    }

    private fun setup() {

        title = "Autentificación"

        registerButton.setOnClickListener {
            if (register_email.text.isNotEmpty() && register_pass.text.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(register_email.text.toString(),
                register_pass.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this@RegisterActivity,"Te has registrado correctamente",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, AllAppActivity::class.java))
                    }else {
                        showAlert()
                    }
                }
            }

            //Guarda los datos en la tabla users
            db.collection("users").document(register_email.text.toString()).set(
                hashMapOf("name" to register_name.text.toString(),
                "surname" to register_surname.text.toString())
            )

        }

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Introduce todos los datos correctamente")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}