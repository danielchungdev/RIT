package com.example.miniproject1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.lang.NumberFormatException
import kotlin.math.pow

class FragmentLoanCalculator : Fragment() {
    private var loanAmount : Double = 0.00
    private var interestRate : Int = 0

    private var fiveEMI : TextView? = null
    private var fiveFinal : TextView? = null

    private var tenEMI : TextView? = null
    private var tenFinal : TextView? = null

    private var fifteenEMI : TextView? = null
    private var fifteenFinal : TextView? = null

    private var twentyEMI : TextView? = null
    private var twentyFinal : TextView? = null

    private var twentyfiveEMI : TextView? = null
    private var twentyfiveFinal : TextView? = null

    private var thirtyEMI : TextView? = null
    private var thirtyFinal : TextView? = null

    private var displayInterest: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loan_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null){
            loanAmount = 0.00;
            interestRate = 0;
        }
        else{
            loanAmount = savedInstanceState.getDouble(LOAN);
            interestRate = savedInstanceState.getInt(INTEREST);
        }

        var loanAmountText = view.findViewById<EditText>(R.id.loanAmountNumber);
        var seekBar = view.findViewById<SeekBar>(R.id.interestRateBar);

        loanAmountText.addTextChangedListener(amountTextWatcher);
        seekBar.setOnSeekBarChangeListener(seekBarListener);

        fiveEMI = view.findViewById(R.id.emi_five)
        tenEMI = view.findViewById(R.id.emi_ten)
        fifteenEMI = view.findViewById(R.id.emi_fifteen)
        twentyEMI = view.findViewById(R.id.emi_twenty)
        twentyfiveEMI = view.findViewById(R.id.emi_twentyfive)
        thirtyEMI = view.findViewById(R.id.emi_thirty)

        fiveFinal = view.findViewById(R.id.totalAmount_five)
        tenFinal = view.findViewById(R.id.totalAmount_ten)
        fifteenFinal = view.findViewById(R.id.totalAmount_fifteen)
        twentyFinal = view.findViewById(R.id.totalAmount_twenty)
        twentyfiveFinal = view.findViewById(R.id.totalAmount_twentyfive)
        thirtyFinal = view.findViewById(R.id.totalAmount_thirty)

        displayInterest = view.findViewById(R.id.displayInterest)
    }

    private val seekBarListener = object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            interestRate = progress
            displayInterest?.setText(interestRate.toString())
            println(interestRate.toString());
            updateTable()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }

    private val amountTextWatcher = object: TextWatcher {
        override fun beforeTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            try {
                loanAmount = text.toString().toDouble()
            }
            catch (e: NumberFormatException) {
                loanAmount = 0.00
            }
            updateTable()
        }

        override fun afterTextChanged(editable: Editable?) {
        }
    }

    private fun updateTable(){

        val yearFiveEmi : Double = calculateEmi(5)
        val fiveTotal: Double = yearFiveEmi * 5.00 * 12.00

        fiveEMI?.setText(String.format("%.2f", yearFiveEmi))
        fiveFinal?.setText(String.format("%.2f", fiveTotal))

        val yearTenEmi : Double = calculateEmi(10)
        val tenTotal: Double = yearFiveEmi * 5.00 * 12.00

        tenEMI?.setText(String.format("%.2f", yearTenEmi))
        tenFinal?.setText(String.format("%.2f", tenTotal))

        val yearFifteenEmi : Double = calculateEmi(15)
        val fifteenTotal: Double = yearFiveEmi * 5.00 * 12.00

        fifteenEMI?.setText(String.format("%.2f", yearFifteenEmi))
        fifteenFinal?.setText(String.format("%.2f", fifteenTotal))

        val yearTwentyEmi : Double = calculateEmi(20)
        val twentyTotal: Double = yearFiveEmi * 5.00 * 12.00

        twentyEMI?.setText(String.format("%.2f", yearTwentyEmi))
        twentyFinal?.setText(String.format("%.2f", twentyTotal))

        val yearTwentyFiveEmi : Double = calculateEmi(25)
        val twentyFiveTotal: Double = yearFiveEmi * 5.00 * 12.00

        twentyfiveEMI?.setText(String.format("%.2f", yearTwentyFiveEmi))
        twentyfiveFinal?.setText(String.format("%.2f", twentyFiveTotal))

        val yearThirtyEmi : Double = calculateEmi(30)
        val thirtyTotal: Double = yearFiveEmi * 5.00 * 12.00

        thirtyEMI?.setText(String.format("%.2f", yearThirtyEmi))
        thirtyFinal?.setText(String.format("%.2f", thirtyTotal))
    }

    private fun calculateEmi(years: Int) : Double {
        if (interestRate == 0){
            return 0.00;
        }
        val n: Double = (years * 12).toDouble();
        val r: Double = interestRate / 1200.00;
        val power: Double = (1.00 + r).pow(n);
        println(years);
        println((loanAmount * r * power) / (power - 1.00))
        return (loanAmount * r * power) / (power - 1.00);
    }

    companion object {
        private const val LOAN = "LOAN";
        private const val INTEREST = "INTEREST"
    }
}