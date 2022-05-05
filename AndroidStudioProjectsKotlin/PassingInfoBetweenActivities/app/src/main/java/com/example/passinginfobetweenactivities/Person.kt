package com.example.passinginfobetweenactivities

import java.io.Serializable

data class Person(
    val name: String,
    val age: String,
    val country: String
) : Serializable {

}