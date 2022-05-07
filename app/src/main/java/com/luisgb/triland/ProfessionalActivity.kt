package com.luisgb.triland

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

class ProfessionalActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var professionalArrayList: ArrayList<ProfessionalModel>
    lateinit var professionalAdapter: ProfessionalAdapter
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional)

        recyclerView = findViewById(R.id.recyclerProfessionals)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        professionalArrayList = arrayListOf()

        professionalAdapter = ProfessionalAdapter(this, professionalArrayList)

        recyclerView.adapter = professionalAdapter

        EventChangeListener()

    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("professionals")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("Firestore error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            professionalArrayList.add(dc.document.toObject(ProfessionalModel::class.java))
                        }
                    }
                    professionalAdapter.notifyDataSetChanged()
                }
            })
    }

}