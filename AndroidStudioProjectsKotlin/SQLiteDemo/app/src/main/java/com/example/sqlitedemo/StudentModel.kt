package com.example.sqlitedemo

import kotlin.random.Random

data class StudentModel(
    var id: Int = getAutoId(),
    var name: String = "",
    var email: String = ""
){

    /*
    A companion object is created once --> like static in java
     */

    companion object {
        private var studentIdList = mutableListOf<Int>()

        private fun getAutoId() : Int {
            var id = Random.nextInt(100000)
            while (checkIdDub(id)) {
                id = Random.nextInt(100000)
            }
            return id
        }

        private fun checkIdDub(id: Int) : Boolean{
            return studentIdList.contains(id)
        }
    }
}