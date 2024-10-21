package com.rum.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rum.android.R
import com.rum.android.models.Product

import com.bumptech.glide.Glide

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]

        holder.name.text = product.name
        holder.description.text = "Description: ${product.description}"
        holder.price.text = "Price: ${product.price}"
        holder.quantity.text = "Quantity: ${product.quantity}"


        Glide.with(holder.itemView.context)
            .load(product.image)
            .centerCrop()
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.product_name)
        val description: TextView = itemView.findViewById(R.id.product_description)
        val price: TextView = itemView.findViewById(R.id.product_price)
        val quantity: TextView = itemView.findViewById(R.id.product_quantity)
        val imageView: ImageView = itemView.findViewById(R.id.product_image)
    }
}
