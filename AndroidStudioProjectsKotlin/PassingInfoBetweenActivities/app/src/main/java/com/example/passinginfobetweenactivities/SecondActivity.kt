package com.example.passinginfobetweenactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        val name = intent.getStringExtra("EXTRA_NAME")
//        val age = intent.getStringExtra("EXTRA_AGE")
//        val country = intent.getStringExtra("EXTRA_COUNTRY")

        val person = intent.getSerializableExtra("EXTRA_PERSON") as Person
        val name = person.name
        val age = person.age
        val country = person.country

        tvName.text = name
        tvAge.text = age
        tvCountry.text = country
    }
}