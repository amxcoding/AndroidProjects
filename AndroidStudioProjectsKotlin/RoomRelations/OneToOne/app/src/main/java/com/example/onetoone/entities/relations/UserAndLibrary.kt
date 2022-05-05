package com.example.onetoone.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.onetoone.entities.Library
import com.example.onetoone.entities.User

data class UserAndLibrary(
    @Embedded
    val user: User,
    // Note for below: names must match
    @Relation(
        parentColumn = "id", // refers to the column id in user table
        entityColumn = "userId" // refers to the column userId in library table
    )
    val library: Library
) {
}