package com.aplikasikaryaanakbangkit.sentiment.welcome

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: WelcomeActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = WelcomeFragment1()
            1 -> fragment = WelcomeFragment2()
            2 -> fragment = WelcomeFragment3()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 3

}