package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }

    fun openToast(view: View){
        val myToast = Toast.makeText(this, "Hello toast", Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun count(view: View){
        println("Im clicked");
        var value = binding.countView.text;
        var valueInt = value.toString().toInt();
        valueInt++;
        binding.countView.text = valueInt.toString();
    }
}