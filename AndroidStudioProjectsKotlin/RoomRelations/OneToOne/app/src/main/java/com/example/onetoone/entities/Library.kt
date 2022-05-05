package com.example.onetoone.entities

import androidx.room.PrimaryKey

data class Library(
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int // refers to the user id
) {
}