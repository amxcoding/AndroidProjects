package com.example.navigationcomponents2020

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.navigationcomponents2020.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private val TAG = "SecondFragment"
    private lateinit var binding: FragmentSecondBinding
    val args: SecondFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)

        val myNumber = args.number
        binding.tvSecond.text = myNumber.toString()
        binding.tvSecond.setOnClickListener {
            val action = SecondFragmentDirections.actionNavigateSecondToFirstFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

}