package com.luisgb.triland

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_forgot_pass.*


class ForgotPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        //Añadimos funcionalidad al texto/botón "Inicia sesión"
        iniciasesionTextView2.setOnClickListener {
            //Para no crear infinitas ventanas si se usa varias veces
            onBackPressed()

            forgotpassButton.setOnClickListener {

                val email: String = forgotpass_email.text.toString().trim() { it <= ' ' }
                if (email.isEmpty()) {
                    Toast.makeText(this@ForgotPassActivity, "Por favor, introduce tu correo electrónico", Toast.LENGTH_SHORT).show()
                }else{
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@ForgotPassActivity, "Se ha enviado un correo electrónico para restablecer la contraseña", Toast.LENGTH_SHORT).show()

                            //Volvemos al LoginActivity
                            finish()
                        }else {
                            Toast.makeText(this@ForgotPassActivity, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}