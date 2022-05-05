package com.example.roomembedded.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomembedded.R
import com.example.roomembedded.data.model.user.User
import com.example.roomembedded.databinding.RecyclerviewItemBinding
import com.example.roomembedded.util.UserDiffUtil

class ListAdapter(
    val recyclerViewItemListener: RecyclerViewItemListener
): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var oldUserList = emptyList<User>()
    private lateinit var binding: RecyclerviewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentUser = oldUserList[position]
        val currentAddress = currentUser.address

        holder.itemView.apply {
            binding.tvFirstName.text = currentUser.firstName
            binding.tvLastName.text = currentUser.lastName
            binding.tvId.text = currentUser.id.toString()
            binding.tvStreetName.text = currentAddress.streetName
            binding.tvHouseNumber.text = currentAddress.houseNumber.toString()
        }

        binding.ibtnDeleteUser.setOnClickListener {
            recyclerViewItemListener.onClickRecyclerViewItem(currentUser)
        }
    }

    override fun getItemCount(): Int {
        return oldUserList.size
    }

    fun setUserList(newUserList: List<User>) {
        val diffUtil = UserDiffUtil(oldUserList, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldUserList = newUserList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        init {
            binding = RecyclerviewItemBinding.bind(view) // adapter binding
        }
    }

    interface RecyclerViewItemListener {
        fun onClickRecyclerViewItem(user: User)
    }



}