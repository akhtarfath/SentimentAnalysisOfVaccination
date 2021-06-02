package com.aplikasikaryaanakbangkit.sentiment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aplikasikaryaanakbangkit.sentiment.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_news,
                R.id.navigation_sentiment_analysis,
                R.id.navigation_statistic_vaccination,
                R.id.navigation_team,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private var exit = false
    override fun onBackPressed() {
        if (exit) {
            finishAffinity()
        } else {
            Toast.makeText(
                this, getString(R.string.exit_verif),
                Toast.LENGTH_SHORT
            ).show()
            exit = true
            Handler(Looper.getMainLooper()).postDelayed({ exit = false }, 3 * 1000)
        }
    }
}

