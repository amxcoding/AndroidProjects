package com.example.swipeableview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = listOf(
            R.drawable.wallpaper1,
            R.drawable.wallpaper2,
            R.drawable.wallpaper3,
            R.drawable.wallpaper4,
            R.drawable.wallpaper5,
            R.drawable.wallpaper6,
            R.drawable.wallpaper7,
            R.drawable.wallpaper8,
            R.drawable.wallpaper9,
            R.drawable.wallpaper10
        )

        val adapter = ViewPagerAdapter(images)
        viewPager.adapter = adapter

        // change swipe orientation
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        // swipe programmatically
        viewPager.beginFakeDrag()
        viewPager.fakeDragBy(-10f)
        viewPager.endFakeDrag()


    }
}