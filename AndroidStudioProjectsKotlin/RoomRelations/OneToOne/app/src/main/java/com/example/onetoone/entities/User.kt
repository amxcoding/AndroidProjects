package com.example.onetoone.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val age: Int
)