package com.example.sharedviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private var _country = MutableLiveData("Serbia")
    val country: LiveData<String> get() = _country

    fun saveCountry(newCountry: String) {
        _country.value = newCountry
    }

}