package com.example.kotlincoroutines

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlincoroutines.databinding.FragmentFirstBinding
import kotlinx.coroutines.*


class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var binding: FragmentFirstBinding
    private val scope = CoroutineScope(Dispatchers.IO + CoroutineName("MyScope"))
    private val TAG = "FirstFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        scope.launch {
            val job1 = launch {

                /*
                You cant cancel a job that is still running to make it cancelable add a delay/yield
                or check if isActive or call methode ensureActive()
                 */
                while (isActive) {
                    Log.d(TAG, "Job 1 running ... ")

                }
            }
            delay(1000L)

            job1.cancel()
            Log.d(TAG, "Job 1 canceled!")
        }


//        // GlobalScope lives as long our application lives
//        lifecycleScope.launch {
//
//            while (true) {
//                delay(1000L)
//                Log.d(TAG, "Running...")
//            }
//        }

        binding.btnGo.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: ")
        super.onStop()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: ")
        super.onResume()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }
    
}