package com.aplikasikaryaanakbangkit.sentiment.vaccination

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository

class VaccinationViewModel(
    private val _repository: SAVRepository
) : ViewModel() {

    val getVaccination = _repository.getVaccination()
    val getTahapanSdm = _repository.getTahapanSDM()
    val getTahapanLansia = _repository.getTahapanLansia()
    val getTahapanPetugas = _repository.getTahapanPetugas()
    val getCakupanVaccination = _repository.getCakupanVaccination()
}