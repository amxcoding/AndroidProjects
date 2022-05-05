package com.example.roomembedded.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomembedded.R
import com.example.roomembedded.data.model.user.Address
import com.example.roomembedded.data.model.user.User
import com.example.roomembedded.data.viewmodel.UserViewModel
import com.example.roomembedded.databinding.FragmentAddBinding

class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var binding: FragmentAddBinding
    private lateinit var userViewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)

        //UserViewModel --> to add new user
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            insertDataToDataBase()
        }

    }


    private fun insertDataToDataBase() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val streetName = binding.etStreetName.text.toString()
        val houseNumber = binding.etHouseNumber.text

        if (inputCheck(firstName, lastName, streetName, houseNumber)) {
            val user = User(0, firstName, lastName, Address(streetName, houseNumber.toString().toInt()))

            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "$firstName successfully added!", Toast.LENGTH_SHORT)
                .show()

            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }


    private fun inputCheck(firstName: String, lastName: String, streetName: String, houseNumber: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(streetName) && houseNumber.isEmpty())
    }

}