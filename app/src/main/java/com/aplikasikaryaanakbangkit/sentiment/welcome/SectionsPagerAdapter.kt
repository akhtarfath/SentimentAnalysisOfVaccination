package com.aplikasikaryaanakbangkit.sentiment.welcome

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> WelcomeFragment1()
                1 -> WelcomeFragment2()
                2 -> WelcomeFragment3()
                else -> Fragment()
            }

    override fun getCount(): Int = 3

}