package com.luisgb.triland

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val user = Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //Logout
        logOut()

        //Datos
        data()

        //Funciones botones
        changeData()
        saveData()

    }

    private fun data() {
        if (user != null) {
            emailTextView.text = user.email
            getData()
        }
    }

    private fun changeData() {
        changeButton.setOnClickListener {
            saveButton.visibility = View.VISIBLE

            nameEditText.visibility = View.VISIBLE
            surnameEditText.visibility = View.VISIBLE
            adressEditText.visibility = View.VISIBLE
            phoneEditText.visibility = View.VISIBLE

            nameTextView.visibility = View.GONE
            surnameTextView.visibility = View.GONE
            adressTextView.visibility = View.GONE
            phoneTextView.visibility = View.GONE

            changeButton.visibility = View.GONE

            getDataET()

        }
    }

    private fun saveData() {
        saveButton.setOnClickListener {
            //val email = user!!.email
            saveButton.visibility = View.GONE

            nameEditText.visibility = View.GONE
            surnameEditText.visibility = View.GONE
            adressEditText.visibility = View.GONE
            phoneEditText.visibility = View.GONE

            nameTextView.visibility = View.VISIBLE
            surnameTextView.visibility = View.VISIBLE
            adressTextView.visibility = View.VISIBLE
            phoneTextView.visibility = View.VISIBLE

            changeButton.visibility = View.VISIBLE

            getData()
            setData()

        }
    }

    private fun getData() {
        db.collection("users").document(user!!.email!!).get().addOnSuccessListener {
            nameTextView.text = it.get("name") as String?
            surnameTextView.text = it.get("surname") as String?
            adressTextView.text = it.get("adress") as String?
            phoneTextView.text = it.get("phone") as String?
        }
    }

    private fun setData() {
        db.collection("users").document(user!!.email!!).set(
            hashMapOf(
                "name" to nameEditText.text.toString(),
                "surname" to surnameEditText.text.toString(),
                "phone" to phoneEditText.text.toString(),
                "adress" to adressEditText.text.toString()
            )
        )
    }

    private fun getDataET() {
        db.collection("users").document(user!!.email!!).get().addOnSuccessListener {
            nameEditText.setText(it.get("name") as String?)
            surnameEditText.setText(it.get("surname") as String?)
            adressEditText.setText(it.get("adress") as String?)
            phoneEditText.setText(it.get("phone") as String?)
        }
    }

    private fun logOut() {
        logoutButton.setOnClickListener {
            //Cerrar sesión
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            finish()
        }
    }

}