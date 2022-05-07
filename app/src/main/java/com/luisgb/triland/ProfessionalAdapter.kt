package com.luisgb.triland

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProfessionalAdapter(
    val context: Context,
    val professionals: ArrayList<ProfessionalModel>
) : RecyclerView.Adapter<ProfessionalAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val email: TextView = itemView.findViewById(R.id.emailRecycler)
        val name: TextView = itemView.findViewById(R.id.nameRecycler)
        val surname: TextView = itemView.findViewById(R.id.surnameRecycler)
        val phone: TextView = itemView.findViewById(R.id.phoneRecycler)
        val description: TextView = itemView.findViewById(R.id.descriptionRecycler)
        val image: ImageView = itemView.findViewById(R.id.imageRecycler)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem : ProfessionalModel = professionals[position]

        Glide.with(context).load(currentItem.image).into(holder.image)

        holder.email.text = currentItem.email
        holder.name.text = currentItem.name
        holder.surname.text = currentItem.surname
        holder.phone.text = currentItem.phone
        holder.description.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return professionals.size
    }

}
