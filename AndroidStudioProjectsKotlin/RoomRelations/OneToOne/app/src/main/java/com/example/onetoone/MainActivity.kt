package com.example.onetoone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.onetoone.database.ULDatabase
import com.example.onetoone.repository.ULRepository
import com.example.onetoone.viewmodel.ULViewModel
import com.example.onetoone.viewmodel.ULViewModelProviderFactory


class MainActivity : AppCompatActivity() {

    lateinit var ulViewModel: ULViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    private fun setupViewModel() {
        val ulRepository = ULRepository(
            ULDatabase(this)
        )

        val ulViewModelProviderFactory = ULViewModelProviderFactory(
            application,
            ulRepository
        )

        ulViewModel = ViewModelProvider(
            this,
            ulViewModelProviderFactory
        ).get(ULViewModel::class.java)


    }
}