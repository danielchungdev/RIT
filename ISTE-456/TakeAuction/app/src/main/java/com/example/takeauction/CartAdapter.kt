package com.example.takeauction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CartAdapter(private var productList : MutableList<Product>): RecyclerView.Adapter<CartAdapter.ProductViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val layout : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false)
        return ProductViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var productImage : ImageView = view.findViewById(R.id.cart_list_image)
        var productTitle : TextView = view.findViewById(R.id.cart_list_title)
        var productPrice : TextView = view.findViewById(R.id.cart_list_price)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productTitle.text = productList[position].title
        holder.productPrice.text = productList[position].price
        Picasso.get().load(productList[position].image).into(holder.productImage)
    }
}