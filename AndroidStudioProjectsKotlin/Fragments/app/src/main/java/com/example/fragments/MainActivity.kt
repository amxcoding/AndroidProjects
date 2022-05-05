package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/*
Fragments are UI components that can be easily reused.
Fragments have their own life cycle and are light weighted compared to activities.
An activity can hold one or more fragments and the activity affects the fragment lifecycle.

You can create a static fragment which is always the same or a dynamic fragment inside a container which can be changed

Static: make a fragment tag and set the name to the fragment
Dynamic: make a FrameLayout and add the fragment at runtime
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()

        // to replace the content of our framelayout
        supportFragmentManager.beginTransaction().apply {
            // to set initial fragment
            replace(R.id.flFragment, firstFragment)
            commit()
        }

        btnFragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, firstFragment)
                addToBackStack(null)
                commit()
            }
        }

        btnFragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, secondFragment)
                addToBackStack(null)
                commit()
            }
        }


    }
}