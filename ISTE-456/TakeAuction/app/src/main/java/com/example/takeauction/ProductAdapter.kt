package com.example.takeauction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class ProductAdapter(private var productList : MutableList<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private lateinit var database : DatabaseReference

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductViewHolder {
        val layout : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_card_view, parent, false)
        return ProductViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {

        var discountedPrice = productList[position].price.toDouble() * (productList[position].discount.toDouble() / 100)
        discountedPrice = productList[position].price.toDouble() - discountedPrice
        holder.productprice.text = "%.2f".format(discountedPrice)
        if (productList[position].discount != "0"){
            holder.productDiscount.text = productList[position].discount + "% off!"
        }
        else{
            holder.productDiscount.text = ""
        }

        Picasso.get().load(productList[position].image).into(holder.productImage)
        holder.productTitle.text = productList[position].title
        holder.addButton.setOnClickListener {
            database = FirebaseDatabase.getInstance().getReference("cart")
            var newProduct = Product(productList[position].image,productList[position].title, "%.2f".format(discountedPrice),  productList[position].discount)
            database.child(position.toString()).setValue(newProduct).addOnSuccessListener {
                Toast.makeText(holder.itemView.context, "Item added to cart", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(holder.itemView.context, "Couldn't add item to cart!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var productImage : ImageView = view.findViewById(R.id.product_image)
        var productTitle : TextView = view.findViewById(R.id.product_title)
        var productDiscount : TextView = view.findViewById(R.id.product_discount)
        var productprice : TextView = view.findViewById(R.id.product_price)
        var addButton : Button = view.findViewById(R.id.addButton)
    }
}