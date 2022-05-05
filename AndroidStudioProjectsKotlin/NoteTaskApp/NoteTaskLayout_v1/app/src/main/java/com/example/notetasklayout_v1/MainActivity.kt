package com.example.notetasklayout_v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notetasklayout_v1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController // needed to navigate to fragments from bottomNav
    private lateinit var navHostFragment: NavHostFragment // needed to get navController
    private lateinit var appBarConfiguration: AppBarConfiguration // needed for toolbar display

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.notesFragment, R.id.tasksFragment),
            binding.drawerLayout // id drawer layout needed for setting up the hamburger icon
        )

        setSupportActionBar(binding.toolbar) // setup custom toolbar
        binding.bottomNavigationView.setupWithNavController(navController) // needed for setting up bottom navigation
        binding.drawerNavigationView.setupWithNavController(navController) // needed for setting up the drawer
        setupActionBarWithNavController(navController, appBarConfiguration) // needed for toolbar back button gone at top level destination

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp() // add appBarConfiguration as parameter for the drawer
    }

}