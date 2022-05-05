package com.example.livedatademo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedatademo.databinding.ActivityMainBinding

/**
 * The view is an activity or a fragment. Here we only set the views.
 * The viewmodel is where all the logic happens.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        viewModel.seconds().observe(this, Observer {
            binding.tvId.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
          if (it) {
              Toast.makeText(this, "Finished!", Toast.LENGTH_SHORT).show()
              binding.btnStart.isClickable = true
          }
        })


        binding.btnStart.setOnClickListener {
            val stopped = viewModel.stopped.value

            if (stopped == false) {
                if (binding.etInput.text.isEmpty() || binding.etInput.text.length < 4) {
                    Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show()
                } else {
                    val milliSeconds = binding.etInput.text.toString().toLong()
                    viewModel.startTimer(milliSeconds)
                }
            } else {
                viewModel.resumeTimer()
                binding.btnStart.text = "Start"
            }
            binding.btnStart.isClickable = false
            binding.btnStop.isClickable = true
        }

        binding.btnStop.setOnClickListener {
            viewModel.stopTimer()
            binding.btnStart.text = "Resume"
            binding.btnStart.isClickable = true
            binding.btnStop.isClickable = false
        }

    }
}