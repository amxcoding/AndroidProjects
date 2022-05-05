package com.example.navigationcomponents2020

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navigationcomponents2020.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var listener: NavController.OnDestinationChangedListener

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Start code for drawer
         */
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()
        drawerLayout = binding.drawerLayout
        binding.navigationView.setupWithNavController(navController)

        // needed for actionbar title
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        /**
         * End code for drawer
         */

        listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.firstFragment) {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.black, null)))

            } else if (destination.id == R.id.secondFragment){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.white, null)))

            }
        }

    }

    override fun onPause() {
        navController.removeOnDestinationChangedListener(listener)
        super.onPause()
    }

    override fun onResume() {
        navController.addOnDestinationChangedListener(listener)
        super.onResume()
    }

    /**
     * Needed to be able to use the menu button on the top bar.
     * Automatically takes care of the back button.
     * Change the label to change the title
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}