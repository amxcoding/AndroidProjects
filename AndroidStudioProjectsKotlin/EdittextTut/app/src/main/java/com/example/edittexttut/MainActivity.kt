package com.example.edittexttut

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            add()
        }
    }

    private fun add () {
        val etFirst = etFirst.text.toString().toInt()
        val etSecond = etSecond.text.toString().toInt()
        tvResult.text = (etFirst + etSecond).toString()


    }

}