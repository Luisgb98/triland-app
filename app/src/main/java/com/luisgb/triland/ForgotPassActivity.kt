package com.luisgb.triland

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_pass.*


class ForgotPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        setup()
        login()
    }

    private fun setup() {
        forgotpassButton.setOnClickListener {

            val email: String = forgotpass_email.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                Toast.makeText(
                    this@ForgotPassActivity,
                    (R.string.setEmailOnForgot),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@ForgotPassActivity,
                                (R.string.sendEmailonForgot),
                                Toast.LENGTH_SHORT
                            ).show()

                            //Volvemos al LoginActivity
                            finish()
                        } else {
                            Toast.makeText(
                                this@ForgotPassActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }

    private fun login() {
        //Añadimos funcionalidad al texto/botón "Inicia sesión"
        iniciasesionTextView2.setOnClickListener {
            //Para no crear infinitas ventanas si se usa varias veces
            onBackPressed()
        }
    }

}