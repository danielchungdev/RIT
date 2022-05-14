package com.example.takeauction

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class HomeFragment : Fragment() {
    var recyclerView :RecyclerView? = null;
    var productArray = ArrayList<Product>()
    private var db : FirebaseDatabase? = null
    private var reference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateDiscount(date : String): String {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy")
        var ogDate = LocalDate.parse(date, dateTimeFormatter)
        val todayDate = LocalDate.now()
        var diff = ChronoUnit.DAYS.between(ogDate.atStartOfDay(), todayDate.atStartOfDay())
        return diff.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        db = FirebaseDatabase.getInstance()
        reference = db?.getReference("products")

        val FirebaseListener = object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(snapshot: DataSnapshot) {
                val child = snapshot.children
                child.forEach{
                    var discount: String = calculateDiscount(it.child("date").value.toString());

                    var product = Product(it.child("image").value.toString(),
                    it.child("title").value.toString(), it.child("price").value.toString(), discount)

                    productArray.add(product)
                }

                val adapter = ProductAdapter(productArray)
                recyclerView?.adapter = adapter
            }
            override fun onCancelled(error: DatabaseError) {
            }

        }
        reference?.addValueEventListener(FirebaseListener)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view);
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = GridLayoutManager(context,
        2,
        GridLayoutManager.VERTICAL,
        false)

        return view
    }
}