package com.example.apihomework

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.apihomework.databinding.ActivityMainBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.text.DecimalFormat


/**
 * A simple [Fragment] subclass.
 * Use the [Item.newInstance] factory method to
 * create an instance of this fragment.
 */
class Item : Fragment() {

    private var product: TextView? = null
    private var description: TextView? = null
    private var discount: TextView?= null
    private var total: TextView? = null

    var okHttpClient: OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var button = view.findViewById<Button>(R.id.button)
        product = view.findViewById(R.id.product)
        description = view.findViewById(R.id.description)
        discount = view.findViewById(R.id.discount)
        total = view.findViewById(R.id.total)
        button.setOnClickListener { randomItem() }
    }

    private fun randomItem(){
        val randomItem = (1..20).random()
        val apiURL = "https://fakestoreapi.com/products/" + randomItem.toString()
        val request: Request = Request.Builder().url(apiURL)
            .addHeader("Accept", "application/json")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("FAILURE!")
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response?.body?.string()
                val title = (JSONObject(json).get("title")).toString()
                val descriptionItem = (JSONObject(json).get("description")).toString()
                var price = (JSONObject(json).get("price")).toString()
                val randomDiscount = (10..70).random();
                val discountAmount =  price.toDouble() * (randomDiscount / 100.0f)
                price = (price.toDouble() - discountAmount).toString()
                product?.setText(title)
                description?.setText(descriptionItem)
                discount?.setText("Discount: " + randomDiscount.toString() + "%")
                total?.setText("Total: " + String.format("%.2f", price.toDouble()))
            }
        })
    }


}