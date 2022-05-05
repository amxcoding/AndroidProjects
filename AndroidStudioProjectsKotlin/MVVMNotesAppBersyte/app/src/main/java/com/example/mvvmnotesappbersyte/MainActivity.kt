package com.example.mvvmnotesappbersyte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmnotesappbersyte.database.NoteDatabase
import com.example.mvvmnotesappbersyte.databinding.ActivityMainBinding
import com.example.mvvmnotesappbersyte.repository.NoteRepository
import com.example.mvvmnotesappbersyte.viewmodel.NoteViewModel
import com.example.mvvmnotesappbersyte.viewmodel.NoteViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup actionbar
        setSupportActionBar(binding.toolbar)

        setupViewModel()

    }

    private fun setupViewModel() {
        val noteRepository = NoteRepository(
            NoteDatabase(this)
        )

        val viewModelProviderFactory = NoteViewModelProviderFactory(
            application,
            noteRepository
        )

        noteViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(NoteViewModel::class.java)

    }


}