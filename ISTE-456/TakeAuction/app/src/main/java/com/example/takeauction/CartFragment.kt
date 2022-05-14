package com.example.takeauction

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.wallet.Wallet
import com.google.firebase.database.*
import org.w3c.dom.Text

class CartFragment : Fragment() {
    var recyclerView : RecyclerView? = null;
    var productArray = ArrayList<Product>()
    private var db : FirebaseDatabase? = null
    private var reference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        db = FirebaseDatabase.getInstance()
        reference = db?.getReference("cart")

        val FirebaseListener = object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(snapshot: DataSnapshot) {
                val child = snapshot.children
                child.forEach{
                    val newProduct = Product(it.child("image").value.toString(), it.child("title").value.toString(),
                        it.child("price").value.toString(), it.child("discount").value.toString())
                    var total : Double = view.findViewById<TextView>(R.id.cart_total).text.toString().replace("$", "").toDouble()
                    total += it.child("price").value.toString().toDouble()
                    view.findViewById<TextView>(R.id.cart_total).text = total.toString()
                    productArray.add(newProduct)
                }
                val adapter = CartAdapter(productArray)
                recyclerView?.adapter = adapter
            }
            override fun onCancelled(error: DatabaseError) {
            }
        }
        reference?.addValueEventListener(FirebaseListener)

        recyclerView = view.findViewById<RecyclerView>(R.id.cart_recycler);
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = GridLayoutManager(context,
            1,
            GridLayoutManager.VERTICAL,
            false)


        view.findViewById<Button>(R.id.payButton).setOnClickListener {
            paymentUsingGooglePay();
        }

        return view
    }

    private fun paymentUsingGooglePay() {
        reference?.removeValue();
        Toast.makeText(context, "Thank you for using Take Auction!", Toast.LENGTH_SHORT).show()
        var navigation = activity as IFragmentNavigation
        navigation.navigateFrag(CartFragment(), false)
    }



}