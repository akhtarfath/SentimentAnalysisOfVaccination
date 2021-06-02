package com.aplikasikaryaanakbangkit.sentiment.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aplikasikaryaanakbangkit.sentiment.MainActivity
import com.aplikasikaryaanakbangkit.sentiment.core.prefs.WelcomePreference
import com.aplikasikaryaanakbangkit.sentiment.core.prefs.entitiy.WelcomeEntity
import com.aplikasikaryaanakbangkit.sentiment.databinding.ActivityWelcomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class WelcomeActivity : AppCompatActivity() {

    private lateinit var _activityWelcomeBinding: ActivityWelcomeBinding

    private lateinit var welcomeEntity: WelcomeEntity
    private lateinit var welcomePreference: WelcomePreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityWelcomeBinding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(_activityWelcomeBinding.root)

        setPager()
        setPrefs()
    }

    private fun setPager() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)

        _activityWelcomeBinding.viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(
            _activityWelcomeBinding.dotTab,
            _activityWelcomeBinding.viewPager
        ) { tab, _ -> tab.text = "" }.attach()

        supportActionBar?.elevation = 0f
    }

    private fun setPrefs() {
        welcomePreference = WelcomePreference(this)
        welcomeEntity = welcomePreference.getLaunchApp()

        if (welcomeEntity.isActive == true) {
            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
        } else {
            with(_activityWelcomeBinding) {
                this.start.setOnClickListener {
                    welcomeEntity.isActive = true
                    welcomePreference.setLaunchApp(welcomeEntity)
                    startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
                }
            }
        }
    }
}