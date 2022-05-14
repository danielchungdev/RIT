package com.example.finalexam

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class TemperatureFragment : Fragment() {
    private var temperature: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_temperature, container, false)
        val temp = view.findViewById<EditText>(R.id.editTextNumber)
        temp.addTextChangedListener(textWatcher)

        val celcius = view.findViewById<Button>(R.id.celcius)
        val farenheit = view.findViewById<Button>(R.id.farenheit)

        celcius.setOnClickListener { calculateCelcius(view) }
        farenheit.setOnClickListener { calculateFarenheit(view) }

        return view
    }

    fun calculateCelcius(v: View){
        hideKeyboard()
        if (temperature == null || temperature == ""){
            Toast.makeText(activity, "You did not enter a temperature to convert.", Toast.LENGTH_LONG).show()
        }
        else {
            var calculation = (5.0 / 9.0) * (temperature?.toInt()!! - 32)
            val textBox = v.findViewById<TextView>(R.id.information)
            textBox.text = temperature.toString() + "° converted to Celcius is: " + calculation.toString() + "°"
        }
    }

    fun calculateFarenheit(v: View){
        hideKeyboard()
        if (temperature == null || temperature == ""){
            Toast.makeText(activity, "You did not enter a temperature to convert.", Toast.LENGTH_LONG).show()
        }
        else {
            var calculation = (9.0 / 5.0*temperature?.toInt()!!) + 32
            val textBox = v.findViewById<TextView>(R.id.information)
            textBox.text = temperature.toString() + "° converted to Farenheit is: " + calculation.toString() + "°"
        }
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun afterTextChanged(p0: Editable?) {
            temperature = p0.toString()
        }

    }
}