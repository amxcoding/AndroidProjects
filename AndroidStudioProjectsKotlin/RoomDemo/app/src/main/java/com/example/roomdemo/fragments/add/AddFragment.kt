package com.example.roomdemo.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdemo.R
import com.example.roomdemo.data.model.user.User
import com.example.roomdemo.data.viewmodel.ViewModelUsers
import com.example.roomdemo.databinding.FragmentAddBinding

class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModelUsers: ViewModelUsers // to add/ modify the database through a viewModel.

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)

        viewModelUsers = ViewModelProvider(this).get(ViewModelUsers::class.java)

        binding.btnAdd.setOnClickListener {
            insertDataToDataBase()
        }
    }

    private fun insertDataToDataBase() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text

        if (inputCheck(firstName, lastName, age)) {
            // create user object
            val user = User(0, firstName, lastName, age.toString().toInt())

            // add data to database
            viewModelUsers.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show() // toast will be shown after navigating back

            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}