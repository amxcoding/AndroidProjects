package com.example.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmdemo.adapters.NicePlaceAdapter
import com.example.mvvmdemo.databinding.ActivityMainBinding
import com.example.mvvmdemo.models.NicePlace
import com.example.mvvmdemo.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private var nicePlaceAdapter: NicePlaceAdapter? = null
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.init()
        // observe changes to our viewmodel or livedata objects
        val observerNicePlaceList = Observer<List<NicePlace>>{
            nicePlaceAdapter?.notifyDataSetChanged()

        }
        mainActivityViewModel.nicePlacesViewModel?.observe(this, observerNicePlaceList)

        mainActivityViewModel.isUpdating.observe(this, {
            if (it) {
                showProgressBar()
                Log.d(TAG, "onCreate: it", )
            } else {
                hideProgressBar()
                binding.recyclerView.smoothScrollToPosition(mainActivityViewModel.nicePlacesViewModel?.value!!.size - 1)
            }
        })

        binding.floatingActionButton.setOnClickListener {
            mainActivityViewModel.addNewValue(
                NicePlace(
                    imageUrl = "https://i.imgur.com//ZcLLrkY.jpg",
                    title = "Washington"
                ),
                true
            )
        }



        // Setup recyclerview
        initRecylcerView()

    }

    private fun initRecylcerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        nicePlaceAdapter = NicePlaceAdapter(this, mainActivityViewModel.nicePlacesViewModel?.value!!)
        binding.recyclerView.adapter = nicePlaceAdapter
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.showContextMenu()
        Log.d(TAG, "showProgressBar: CALLED")
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }


}