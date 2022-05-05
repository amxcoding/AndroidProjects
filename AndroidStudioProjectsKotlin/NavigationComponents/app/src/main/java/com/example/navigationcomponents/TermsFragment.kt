package com.example.navigationcomponents

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.navigationcomponents.databinding.FragmentTermsBinding

class TermsFragment : Fragment(R.layout.fragment_terms) {

    private lateinit var binding: FragmentTermsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTermsBinding.bind(view)


    }


}