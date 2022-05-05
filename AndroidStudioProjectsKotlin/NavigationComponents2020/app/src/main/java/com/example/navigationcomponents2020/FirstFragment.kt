package com.example.navigationcomponents2020

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.navigationcomponents2020.databinding.FragmentFirstBinding


class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var binding: FragmentFirstBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        binding.tvFirst.setOnClickListener {
            val action = FirstFragmentDirections.actionNavigateFirstToSecondFragment(22)

            Navigation.findNavController(view).navigate(action)
        }

    }

}