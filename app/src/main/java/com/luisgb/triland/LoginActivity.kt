package com.luisgb.triland

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        //Splash
        setTheme(R.style.Theme_Triland)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Evento Analytics
        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integración de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        //Añadimos funcionalidad al texto/botón "Crear una"
        creaunaTextView.setOnClickListener {

            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        //Añadimos funcionalidad al texto/botón "¿Has olvidado tu contraseña?"
        olvidarTextView.setOnClickListener {

            startActivity(Intent(this@LoginActivity, ForgotPassActivity::class.java))
        }

        //setup
        setup()

        auth = FirebaseAuth.getInstance()

        val currentuser = auth.currentUser
        if(currentuser != null) {
            startActivity(Intent(this@LoginActivity, AllAppActivity::class.java))
            finish()
        }

    }

    private fun setup() {

        logInButton.setOnClickListener {
            if (login_email.text.isNotEmpty() && login_pass.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(login_email.text.toString(),
                    login_pass.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this@LoginActivity,(R.string.successfulLogIn),Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, AllAppActivity::class.java))
                    }else {
                        showAlert()
                    }
                }
            }
        }

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.error)
        builder.setMessage(R.string.wrongEmailPass)
        builder.setPositiveButton(R.string.accept, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}