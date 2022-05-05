package com.example.mvvmdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmdemo.R
import com.example.mvvmdemo.databinding.LayoutRecyclerviewItemBinding
import com.example.mvvmdemo.models.NicePlace

class NicePlaceAdapter(
    private val context: Context,
    private var nicePlacesList: List<NicePlace>
): RecyclerView.Adapter<NicePlaceAdapter.NicePlaceViewHolder>() {

    private val TAG = "NicePlacesAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NicePlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recyclerview_item, parent, false)
        return NicePlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: NicePlaceViewHolder, position: Int) {
        // set the name
        holder.tvPlaceName.text = nicePlacesList[position].title

        // set the image
        val defaultOptions = RequestOptions()
            .error(R.drawable.ic_launcher_foreground)

        Glide.with(context)
            .setDefaultRequestOptions(defaultOptions)
            .load(nicePlacesList[position].imageUrl)
            .into(holder.cimNicePlace)

    }

    override fun getItemCount(): Int {
        return nicePlacesList.size
    }

    inner class NicePlaceViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = LayoutRecyclerviewItemBinding.bind(view)
        val tvPlaceName = binding.tvPlaceName
        val cimNicePlace = binding.cimPlaceImage


    }

}