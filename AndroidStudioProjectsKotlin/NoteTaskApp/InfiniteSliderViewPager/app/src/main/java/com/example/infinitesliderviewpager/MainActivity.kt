package com.example.infinitesliderviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.infinitesliderviewpager.adapter.ImageAdapter
import com.example.infinitesliderviewpager.databinding.ActivityMainBinding
import com.example.infinitesliderviewpager.model.Image

class MainActivity : AppCompatActivity() {

    private lateinit var bindding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindding.root)

        val imageList = ArrayList<Image>()
        val viewPager2 = bindding.viewPager

        imageList.add(Image(R.drawable.image1))
        imageList.add(Image(R.drawable.image2))
        imageList.add(Image(R.drawable.image3))
        imageList.add(Image(R.drawable.image4))

        val imageAdapter = ImageAdapter(imageList, viewPager2)
        viewPager2.adapter = imageAdapter
        imageAdapter.notifyDataSetChanged()

        viewPager2.offscreenPageLimit = 3
        viewPager2.clipChildren = false
        viewPager2.clipToPadding = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER








    }
}