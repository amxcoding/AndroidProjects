package com.example.roomdemo.util

import androidx.recyclerview.widget.DiffUtil
import com.example.roomdemo.data.model.user.User

class UserDiffUtil(
    private val oldList: List<User>,
    private val newList: List<User>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].compareUser(newList[newItemPosition])
        /*
        return when {
                return when {
            user.id != this.id || user.firstname != this.firstname || user.lastName != this.lastName
                    || user.age != this.age -> {
                false
            }
            else -> true
        }
         */
    }
}