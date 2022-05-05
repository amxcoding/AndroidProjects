package com.example.spinnersdropdownview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // custom list made on runtime, also possible to choose list in xml file -> see entries
        val customList = listOf("First", "Second", "Third", "Fourth")
        val adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, customList)
        spMonths.adapter = adapter

        spMonths.onItemSelectedListener = object : AdapterView.OnItemSelectedListener { // anonymous class
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, postion: Int, id: Long) {
                Toast.makeText(this@MainActivity,
                    "You selected ${adapterView?.getItemAtPosition(postion).toString()}",
                Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // there is always something selected
            }
        }


    }
}