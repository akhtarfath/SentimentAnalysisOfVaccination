package com.aplikasikaryaanakbangkit.sentiment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Home page"
    }
    val text: LiveData<String> = _text
}