package com.aplikasikaryaanakbangkit.sentiment.ui.statistic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VaccinationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Statistic page"
    }
    val text: LiveData<String> = _text
}