package com.example.infinitesliderviewpager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.infinitesliderviewpager.R
import com.example.infinitesliderviewpager.databinding.ViewpagerItemBinding
import com.example.infinitesliderviewpager.model.Image

class ImageAdapter(
    private val imageList: List<Image>,
    private val viewPager2: ViewPager2
): RecyclerView.Adapter<ImageAdapter.ImageViewModel>() {




    inner class ImageViewModel(val itemBinding: ViewpagerItemBinding):
        RecyclerView.ViewHolder(itemBinding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewModel {
        return ImageViewModel(
            ViewpagerItemBinding.
            inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageViewModel, position: Int) {
        val currentImage = imageList[position]

        holder.itemBinding.imageView.setImageResource(currentImage.image)


    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}