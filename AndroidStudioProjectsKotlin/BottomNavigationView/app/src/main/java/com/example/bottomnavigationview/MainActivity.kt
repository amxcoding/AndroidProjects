package com.example.bottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

/*
A bottom navigation bar is used to navigate between different fragments
It does not belong to the standard library and needs to be included
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()

        // initial fragmentf
        setCurrentFragment(firstFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.miHome -> setCurrentFragment(firstFragment)
                R.id.miMessages -> setCurrentFragment(secondFragment)
                R.id.miProfile -> setCurrentFragment(thirdFragment)
            }
            true// in a lambda function we dont write return true, just true
        }

        // set the number of messages statically as example
        bottomNavigationView.getOrCreateBadge(R.id.miMessages).apply {
            number = 10
            isVisible = true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)  =
        supportFragmentManager.beginTransaction().apply {
            // to set initial fragment
            replace(R.id.flFragment, fragment)
            commit()
        }

}
