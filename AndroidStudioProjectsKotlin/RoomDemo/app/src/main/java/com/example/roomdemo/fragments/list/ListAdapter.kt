package com.example.roomdemo.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.R
import com.example.roomdemo.data.model.user.User
import com.example.roomdemo.databinding.RecyclerviewItemBinding
import com.example.roomdemo.util.UserDiffUtil
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class ListAdapter(
    private val deleteUserOnClick: DeleteUserOnClick
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var oldUserList = emptyList<User>()
    private lateinit var binding: RecyclerviewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        ) // This line is almost always the same...
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = oldUserList[position]

        holder.itemView.apply {  // holder.itemView verwijst naar de ListViewHolder die de items en de view vasthoudt
            binding.tvFirstName.text = currentItem.firstname
            binding.tvLastName.text = currentItem.lastName
            binding.tvAge.text = currentItem.age.toString()
            binding.tvId.text = currentItem.id.toString()

            ibtnDeleteUser.setOnClickListener {
                deleteUserOnClick.deleteOnClick(currentItem)
            }

            recyclerViewItemConstraintLayout.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                findNavController().navigate(action)
//                Toast.makeText(this.context, currentItem.firstname, Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun getItemCount(): Int {
        return oldUserList.size
    }

    fun setData(newUserList: List<User>) {
        val diffUtil = UserDiffUtil(oldUserList, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldUserList = newUserList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            binding = RecyclerviewItemBinding.bind(view) // adapter binding
        }
    }

    /**
     * Je kan een interface gebruiken om een item door te geven
     */
    interface DeleteUserOnClick {
        fun deleteOnClick(user: User)
    }
}