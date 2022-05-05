package com.example.viewmodeldemo

import androidx.lifecycle.ViewModel

class MainActivityViewModel
    (
): ViewModel() {

    var number = 0

    fun addNumber() {
        number++
    }

}