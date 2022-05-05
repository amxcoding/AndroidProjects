package com.example.roomdemo.data.model.user

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/*
class should be a data class
and contain the fields which will be the table columns
 */
@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) // id is generated automatically
    val id: Int,

    val firstname: String,
    val lastName: String,
    val age: Int,

): Parcelable {

    fun compareUser(user: User): Boolean{
        return when {
            user.id != this.id || user.firstname != this.firstname || user.lastName != this.lastName
                    || user.age != this.age -> {
                false
            }
            else -> true
        }
    }



}

// To embed date/ bitmap you need a type convertor