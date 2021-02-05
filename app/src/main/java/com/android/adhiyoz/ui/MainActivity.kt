package com.android.adhiyoz.ui

import android.content.Context
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.adhiyoz.R
import com.android.adhiyoz.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private val ONLY_BOTTOM_NAV_DESTINATIONS = setOf(
            R.id.navigation_home
        )
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Hide bottom navigation if the selected fragment is not part of bottom navigation menu
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (!ONLY_BOTTOM_NAV_DESTINATIONS.contains(destination.id)) {
                binding.navView.hide()
            } else {
                binding.navView.show()
            }

            // Hide keyboard
            val view = this.currentFocus
            view?.let { v ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // This view extension hide the view by transitioning downwards
    private fun View.hide() {
        val transition = Slide(Gravity.BOTTOM)
        transition.duration = 500
        transition.addTarget(this)
        TransitionManager.beginDelayedTransition(binding.container, transition)
        visibility = View.GONE
    }

    // This view extension shows the view by transitioning upwards
    private fun View.show() {
        val transition = Slide(Gravity.BOTTOM)
        transition.duration = 500
        transition.addTarget(this)
        TransitionManager.beginDelayedTransition(binding.container, transition)
        visibility = View.VISIBLE
    }
}