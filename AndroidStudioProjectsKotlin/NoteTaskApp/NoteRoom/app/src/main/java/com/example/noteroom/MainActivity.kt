package com.example.noteroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.noteroom.database.NoteDatabase
import com.example.noteroom.databinding.ActivityMainBinding
import com.example.noteroom.repository.NoteRepository
import com.example.noteroom.viewmodel.NoteViewModel
import com.example.noteroom.viewmodel.NoteViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration // to fix the back button at top levels, also needed to show the correct action bar title
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // To make the bottom navigation work we need to initialize it
        // we have to merge it with the navController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment // needed to get the navController
        navController = navHostFragment.findNavController()

        // to fix the back button at top level distinations
        appBarConfiguration = AppBarConfiguration(
            // pass all the top level distinations
            setOf(R.id.homeFragment, R.id.noteFragment)
        )

        // pass navController and appBar Configuration
        binding.bottomNavigationView.setupWithNavController(navController)

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