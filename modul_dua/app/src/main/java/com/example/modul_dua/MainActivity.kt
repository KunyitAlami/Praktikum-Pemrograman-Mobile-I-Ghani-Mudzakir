package com.example.modul_dua

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.modul_dua.TipViewModel
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.switchmaterial.SwitchMaterial
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var costInputLayout: TextInputLayout
    private lateinit var costOfServiceEditText: EditText
    private lateinit var tipOptions: RadioGroup
    private lateinit var roundUpSwitch: SwitchMaterial
    private lateinit var calculateButton: Button
    private lateinit var tipResultTextView: TextView

    private val viewModel: TipViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        costInputLayout = findViewById(R.id.cost_input_layout)
        costOfServiceEditText = findViewById(R.id.cost_of_service)
        tipOptions = findViewById(R.id.tip_options)
        roundUpSwitch = findViewById(R.id.round_up_switch)
        calculateButton = findViewById(R.id.calculate_button)
        tipResultTextView = findViewById(R.id.tip_result)

        if (viewModel.tipAmount.isNotEmpty()) {
            tipResultTextView.text = viewModel.tipAmount
        }

        calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val costText = costOfServiceEditText.text.toString()
        val cost = costText.toDoubleOrNull()

        if (cost == null || cost <= 0.0) {
            costInputLayout.error = "Masukkan biaya yang valid"
            Toast.makeText(this, "Input tidak valid! Masukkan angka lebih dari 0", Toast.LENGTH_SHORT).show()
            tipResultTextView.text = "Tip Amount: -"
            return
        } else {
            costInputLayout.error = null // clear error
        }

        val tipPercentage = when (tipOptions.checkedRadioButtonId) {
            R.id.option_amazing -> 0.20
            R.id.option_good -> 0.18
            else -> 0.15
        }

        var tip = cost * tipPercentage
        if (roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        val resultText = "Tip Amount: $formattedTip"
        tipResultTextView.text = resultText

        viewModel.tipAmount = resultText
    }
}
