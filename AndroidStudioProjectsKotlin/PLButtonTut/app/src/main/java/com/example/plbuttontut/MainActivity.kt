package com.example.plbuttontut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val buttonApply = findViewById<Button>(R.id.btnApply) Or:
        btnApply.setOnClickListener {
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val birthDate = etBirthdate.text.toString()
            val country = etCountry.text.toString()

            Log.d(TAG, "onCreate: $firstName $lastName, born on $birthDate, from $country just applied to the formular.")
        }
    }
}