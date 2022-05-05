package com.example.roomembedded.fragments.list

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomembedded.R
import com.example.roomembedded.data.model.user.User
import com.example.roomembedded.data.viewmodel.UserViewModel
import com.example.roomembedded.databinding.CustomDialogueLayoutBinding
import com.example.roomembedded.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list), ListAdapter.RecyclerViewItemListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: ListAdapter
    private lateinit var userViewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        initRecyclerView()

        //UserViewModel --> LiveData List<User> Observer --> als de List<User> wijzigt dan wordt dit aangeroepen
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { userList ->
            adapter.setUserList(userList)
        })

        binding.fabAdd.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToAddFragment()
            Navigation.findNavController(this.requireView()).navigate(action)
        }
    }

    private fun initRecyclerView() {
        adapter = ListAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    // delete user
    override fun onClickRecyclerViewItem(user: User) {
        val message = "Are you sure you want to delete ${user.firstName}?"
        showDialog(message, user)

    }

    private fun showDialog(message: String, user: User) {
        val dialog = Dialog(requireContext()).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.custom_dialogue_layout)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        }

        val body = dialog.findViewById(R.id.tvQuestion) as TextView
        body.text = message
        val yesBtn = dialog.findViewById(R.id.btnYes) as Button
        val noBtn = dialog.findViewById(R.id.btnNo) as TextView
        yesBtn.setOnClickListener {
            userViewModel.deleteUser(user)
            dialog.dismiss()
            Toast.makeText(requireContext(), "Successfully deleted ${user.firstName}", Toast.LENGTH_SHORT).show()
        }
        noBtn.setOnClickListener {  }
        dialog.show()
    }

}