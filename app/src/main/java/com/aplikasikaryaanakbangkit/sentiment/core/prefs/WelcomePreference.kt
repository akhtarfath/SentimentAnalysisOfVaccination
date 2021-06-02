package com.aplikasikaryaanakbangkit.sentiment.core.prefs

import android.content.Context
import com.aplikasikaryaanakbangkit.sentiment.core.prefs.entitiy.WelcomeEntity

internal class WelcomePreference(context: Context) {

    companion object {
        private const val PREFS_NAME = "WELCOME_PREF"
        private const val IS_ACTIVE = "IS_ACTIVE"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setLaunchApp(value: WelcomeEntity) {
        val editor = preferences.edit()
        value.isActive?.let { editor.putBoolean(IS_ACTIVE, it) }
        editor.apply()
    }

    fun getLaunchApp(): WelcomeEntity {
        val model = WelcomeEntity()
        model.isActive = preferences.getBoolean(IS_ACTIVE, false)

        return model
    }
}