package com.aplikasikaryaanakbangkit.sentiment.home

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository

class HomeViewModel(
    _repository: SAVRepository
) : ViewModel() {

    val getGlobalCovid by lazy { _repository.getAllGlobalCovid() }
    val getIDCovid by lazy { _repository.getAllIDCovid() }
}

