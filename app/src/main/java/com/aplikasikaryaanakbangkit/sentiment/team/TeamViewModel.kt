package com.aplikasikaryaanakbangkit.sentiment.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TeamViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Statistic page"
    }
    val text: LiveData<String> = _text
}