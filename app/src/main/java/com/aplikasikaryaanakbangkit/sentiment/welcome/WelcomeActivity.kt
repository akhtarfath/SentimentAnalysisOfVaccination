package com.aplikasikaryaanakbangkit.sentiment.welcome

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.aplikasikaryaanakbangkit.sentiment.MainActivity
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.databinding.ActivityWelcomeBinding
import java.lang.Boolean.TRUE

class WelcomeActivity : AppCompatActivity() {

    private lateinit var _activityWelcomeBinding: ActivityWelcomeBinding
/*
    override fun onResume() {
        super.onResume()

        val sharedPreference: SharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        if(sharedPreference.getBoolean("yes", false)){
            val editor: SharedPreferences.Editor = sharedPreference.edit()
            editor.putBoolean("yes", TRUE)
                    .apply()
        }else{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

 */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityWelcomeBinding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(_activityWelcomeBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        _activityWelcomeBinding.viewPager.adapter = sectionsPagerAdapter

        _activityWelcomeBinding.dotTab.setupWithViewPager(_activityWelcomeBinding.viewPager)

        val sharedPreference: SharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        if(sharedPreference.getBoolean("yes", false)){
            val editor: SharedPreferences.Editor = sharedPreference.edit()
            editor.putBoolean("yes", TRUE)
                    .apply()
        }else{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}