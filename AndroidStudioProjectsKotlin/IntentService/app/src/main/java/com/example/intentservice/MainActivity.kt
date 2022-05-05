package com.example.intentservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
/*
Service for single threading
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}