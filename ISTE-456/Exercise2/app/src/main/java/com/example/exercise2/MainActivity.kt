package com.example.exercise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import com.example.exercise2.databinding.ActivityMainBinding
import android.text.Editable

import android.text.TextWatcher

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var amount: Double = 0.00
    private var interest: Int = 0
    private var flag: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val seek = findViewById<SeekBar>(R.id.interestRateBar)
        val loanField = findViewById<EditText>(R.id.loanAmountNumber)

        loanField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.toString().length > 0) {
                    flag = true
                    setLoanAmount(s.toString().toDouble())
                    updateTable()
                }
                else {
                    flag = false
                    updateTable()
                }
            }
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })

        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                val interestText: TextView = findViewById<TextView>(R.id.displayInterest)
                setInterest(interestText.text.toString().dropLast(1).toInt())
                interestText.text = progress.toString() + "%"
                updateTable()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                val interestText: TextView = findViewById<TextView>(R.id.displayInterest)
                setInterest(interestText.text.toString().dropLast(1).toInt())
                interestText.text = seek.progress.toString() + "%"
                updateTable()
            }
        })
    }

    fun updateTable(){
        val five_emi: TextView = findViewById(R.id.emi_five)
        val ten_emi: TextView = findViewById(R.id.emi_ten)
        val fifteen_emi: TextView = findViewById(R.id.emi_fifteen)
        val twenty_emi: TextView = findViewById(R.id.emi_twenty)
        val twentyfive_emi: TextView = findViewById(R.id.emi_twentyfive)
        val thirty_emi: TextView = findViewById(R.id.emi_thirty)

        val five_total: TextView = findViewById(R.id.totalAmount_five)
        val ten_total: TextView = findViewById(R.id.totalAmount_ten)
        val fifteen_total: TextView = findViewById(R.id.totalAmount_fifteen)
        val twenty_total: TextView = findViewById(R.id.totalAmount_twenty)
        val twentyfive_total: TextView = findViewById(R.id.totalAmount_twentyfive)
        val thirty_total: TextView = findViewById(R.id.totalAmount_thirty)

        if (interest > 0 && flag) {

            five_emi.text = String.format("%.2f", calculateEmi(amount, interest, 5))
            ten_emi.text = String.format("%.2f", calculateEmi(amount, interest, 10))
            fifteen_emi.text = String.format("%.2f", calculateEmi(amount, interest, 15))
            twenty_emi.text = String.format("%.2f", calculateEmi(amount, interest, 20))
            twentyfive_emi.text = String.format("%.2f", calculateEmi(amount, interest, 2))
            thirty_emi.text = String.format("%.2f", calculateEmi(amount, interest, 30))

            five_total.text =
                String.format("%.2f", calculateTotalAmount(calculateEmi(amount, interest, 5), 5))
            ten_total.text =
                String.format("%.2f", calculateTotalAmount(calculateEmi(amount, interest, 10), 10))
            fifteen_total.text =
                String.format("%.2f", calculateTotalAmount(calculateEmi(amount, interest, 15), 15))
            twenty_total.text =
                String.format("%.2f", calculateTotalAmount(calculateEmi(amount, interest, 20), 20))
            twentyfive_total.text =
                String.format("%.2f", calculateTotalAmount(calculateEmi(amount, interest, 25), 25))
            thirty_total.text =
                String.format("%.2f", calculateTotalAmount(calculateEmi(amount, interest, 30), 30))
        }
        else {
            five_emi.text = "0.00"
            ten_emi.text = "0.00"
            fifteen_emi.text = "0.00"
            twenty_emi.text = "0.00"
            twentyfive_emi.text = "0.00"
            thirty_emi.text = "0.00"

            five_total.text = "0.00"
            ten_total.text = "0.00"
            fifteen_total.text = "0.00"
            twenty_total.text = "0.00"
            twentyfive_total.text = "0.00"
            thirty_total.text = "0.00"
        }
    }

    fun calculateEmi(p: Double, x: Int, y: Int): Double{
        val n: Double = (y * 12).toDouble()
        val r: Double = x/1200.00
        val emi:Double = (p * r * Math.pow((1+r), n)) / (Math.pow((1+r), n) - 1)
        return emi
    }

    fun calculateTotalAmount(emi: Double, years: Int): Double{
        val n = years * 12
        return emi * n;
    }

    fun setLoanAmount(amountVal: Double){
        this.amount = amountVal;
    }

    fun setInterest(interest: Int){
        this.interest = interest
    }

}