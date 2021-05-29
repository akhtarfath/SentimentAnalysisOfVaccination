package com.aplikasikaryaanakbangkit.sentiment.home

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository

class HomeViewModel(
    private val _repository: SAVRepository
) : ViewModel() {

    val getGlobalCovid = _repository.getAllGlobalCovid()
    val getIDCovid = _repository.getAllIDCovid()
}

