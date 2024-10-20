package com.rum.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rum.android.R
import com.rum.android.models.Customer

class CustomerAdapter(private val customerList: List<Customer>) :
    RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val customer = customerList[position]
        holder.name.text = customer.name
        holder.cpf.text = "CPF: ${customer.cpf}"
        holder.phone.text = "Phone: ${customer.phone}"
        holder.email.text = "Email: ${customer.email}"
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val cpf: TextView = itemView.findViewById(R.id.cpf)
        val phone: TextView = itemView.findViewById(R.id.phone)
        val email: TextView = itemView.findViewById(R.id.email)
    }
}
