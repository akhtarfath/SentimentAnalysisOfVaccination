package com.aplikasikaryaanakbangkit.sentiment.home

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository

class HomeViewModel(
    private val _repository: SAVRepository
) : ViewModel() {

    val getRecoveredGlobal = _repository.getRecovered()
    val getDeathGlobal = _repository.getDeath()
    val getConfirmGlobal = _repository.getConfirmed()
    val getGlobalCovid = _repository.getAllGlobalCovid()
    val getIDCovid = _repository.getAllIDCovid()
}

