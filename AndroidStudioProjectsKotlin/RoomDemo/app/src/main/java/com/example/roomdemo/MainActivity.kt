package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.roomdemo.databinding.ActivityMainBinding

/**
 * Room advandages:
 * 1. Compile time safety
 * 2. Less boilerplate code
 * 3. Easily intergrated with other Architecture components like live data
 *
 * Room consist of 3 components:
 * 1. @Entity
 * Represents a table within the database. Room creates a table for each that has @Entity annotation, the fields in the class
 * correspond to columns in the table. Therefore, the entity classes tend to be small model classes that don't contain any logic.
 *
 * 2. @Dao
 * DAO (data access object) are responsible for defining the methods that access the database. This is the place where we write our queries.
 * In SQLite we used the cursor object to access items in the database.
 *
 * 3. @Database
 * Contains the database holder and serves as the main access point for the underlying connection to your app's data.
 *
 * */
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // to change the actionbar name
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()

        // needed for back button
        //appBarConfiguration = AppBarConfiguration(navController.graph, null) // use when you have also a drawer
        setupActionBarWithNavController(navController)

    }


    // needed for back button
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}