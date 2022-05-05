package com.example.roomembedded.data.model.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,

    @Embedded
    val address: Address
) {
    fun compareUser(user: User): Boolean{
        return when {
            user.id != this.id || user.firstName != this.firstName ||
                    user.lastName != this.lastName -> {
                false
            }
            else -> true
        }
    }
}


data class Address(
    val streetName: String,
    val houseNumber: Int
)