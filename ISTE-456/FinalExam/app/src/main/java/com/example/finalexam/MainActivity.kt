package com.example.finalexam

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val temperatureFragment = TemperatureFragment()

        supportFragmentManager.beginTransaction()
        .replace(R.id.content, temperatureFragment)
        .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        hideKeyboard()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.content)
        if (currentFragment is AboutFragment){
            println("I'm already used")
        }
        else{
            val aboutFragment = AboutFragment()
            if (item.itemId == R.id.about){
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.content, aboutFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        return true;
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
}