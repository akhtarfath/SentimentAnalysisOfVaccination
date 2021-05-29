package com.aplikasikaryaanakbangkit.sentiment.vaccination

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository

class VaccinationViewModel(
    _repository: SAVRepository
) : ViewModel() {

    val getVaccination = _repository.getVaccination()
    val getVaccinationStepHealthHR = _repository.getVaccinationStepHealthHR()
    val getVaccinationStepElderly = _repository.getVaccinationStepElderly()
    val getVaccinationStepPublicOfficer = _repository.getVaccinationStepPublicOfficer()
    val getVaccinationCoverage = _repository.getVaccinationCoverage()
}