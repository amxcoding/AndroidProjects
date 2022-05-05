package com.example.roomdemo.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.R
import com.example.roomdemo.data.model.user.User
import com.example.roomdemo.data.viewmodel.ViewModelUsers
import com.example.roomdemo.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list), ListAdapter.DeleteUserOnClick {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: ListAdapter
    private lateinit var viewModelUsers: ViewModelUsers
    private val userList = ArrayList<User>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        // Recyclerview
        initRecyclerView()

        // User ViewModel
        viewModelUsers = ViewModelProvider(this).get(ViewModelUsers::class.java)
        viewModelUsers.readAllData.observe(
            viewLifecycleOwner,
            Observer { user -> // readAllData returns a list of users as LiveData
                adapter.setData(user)
            })


        //view.fabAdd.setOnClickListener {  } or
        binding.fabAdd.setOnClickListener {
            //findNavController().navigate(R.id.action_listFragment_to_addFragment) or
            val action = ListFragmentDirections.actionListFragmentToAddFragment()
            Navigation.findNavController(view).navigate(action)
        }

        // Add Menu
        setHasOptionsMenu(true)

    }

    private fun initRecyclerView() {
        adapter = ListAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext()) // user class accepts only integer for age
    }


    // Delete user
    override fun deleteOnClick(user: User) {
        alertDialogue(user.firstname, user)
    }


    // Inflate options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_items_menu, menu)
    }


    // Handle onclick menu item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itemDelete) {
            alertDialogue("all users", null)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun alertDialogue(userName: String, user: User?) {
        val builder = AlertDialog.Builder(requireContext())
            .setPositiveButton("Yes") {
                    _, _ ->
                var toastMessage = "Deleted $userName successfully"
                if (user != null) {
                    viewModelUsers.deleteUser(user)
                } else {
                    viewModelUsers.deleteAllUsers()
                }

                Toast.makeText(this.context, toastMessage, Toast.LENGTH_SHORT)
                    .show()
            }
            .setNegativeButton("No") { _,_ -> }
            .setTitle("Delete ${userName}?")
            .setMessage("Are you sure you want to delete ${userName}?")

        builder.create().show()
    }

}