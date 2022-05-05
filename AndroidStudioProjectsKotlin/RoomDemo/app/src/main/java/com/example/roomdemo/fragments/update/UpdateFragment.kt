package com.example.roomdemo.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdemo.R
import com.example.roomdemo.data.model.user.User
import com.example.roomdemo.data.viewmodel.ViewModelUsers
import com.example.roomdemo.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment(R.layout.fragment_update) {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModelUsers: ViewModelUsers
    private val args by navArgs<UpdateFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBinding.bind(view)
        viewModelUsers = ViewModelProvider(this).get(ViewModelUsers::class.java)

        initView()

        binding.btnUpdate.setOnClickListener {
            updateUser()
        }

        // Add Menu
        setHasOptionsMenu(true)

    }

    private fun initView() {
        val firstName = args.currentUser.firstname
        val lastName = args.currentUser.lastName
        val age = args.currentUser.age.toString()

        binding.etUpdateFirstName.setText(firstName)
        binding.etUpdateLastName.setText(lastName)
        binding.etUpdateAge.setText(age)
    }

    private fun updateUser() {
        val firstName = binding.etUpdateFirstName.text.toString()
        val lastName = binding.etUpdateLastName.text.toString()
        val age = binding.etUpdateAge.text // Editable

        if (inputCheck(firstName, lastName, age)) {
            // create user object
            val user = User(args.currentUser.id, firstName, lastName, age.toString().toInt())

            // update data to database
            viewModelUsers.updateUser(user)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT)
                .show() // toast will be shown after navigating back

            //navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    // Inflate layout for our menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_items_menu, menu)
    }

    // Handle menu items clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itemDelete) {
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        // dialogue builder
        val builder = AlertDialog.Builder(requireContext())
            .setPositiveButton("Yes") { _, _ ->
                viewModelUsers.deleteUser(args.currentUser)
                //navigate back
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)

                Toast.makeText(requireContext(), "Successfully removed ${args.currentUser.firstname}", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No") { _,_ -> }
            .setTitle("Delete ${args.currentUser.firstname}?")
            .setMessage("Are you sure you want to delete ${args.currentUser.firstname}?")

        builder.create().show()


    }

}

