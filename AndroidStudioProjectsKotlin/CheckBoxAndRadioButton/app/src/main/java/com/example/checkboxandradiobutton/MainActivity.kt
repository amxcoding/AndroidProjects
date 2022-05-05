package com.example.checkboxandradiobutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOrder.setOnClickListener {
            getPref()
        }

    }

    fun getPref() {
        val checkedRadioButtonId = radioGroup.checkedRadioButtonId
        val checkedMeat = findViewById<RadioButton>(checkedRadioButtonId)
        val cheese = cbCheese.isChecked
        val onion = cbOnions.isChecked
        val salade = cbSalad.isChecked
        val message = "You ordered a burger with:" +
                "\n${checkedMeat.text}" +
                (if (cheese) "\n${cbCheese.text}" else "" ) +
                (if (salade) "\n${cbSalad.text}" else "" ) +
                (if (onion) "\n${cbOnions.text}" else "" )


        tvOrder.text = message
        Toast.makeText(this , "Ordered", Toast.LENGTH_LONG).show()
    }

}