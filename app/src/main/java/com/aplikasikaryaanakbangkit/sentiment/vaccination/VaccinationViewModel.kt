package com.aplikasikaryaanakbangkit.sentiment.vaccination

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository

class VaccinationViewModel(
    _repository: SAVRepository
) : ViewModel() {

    val getVaccination by lazy { _repository.getVaccination() }
    val getVaccinationStepHealthHR by lazy { _repository.getVaccinationStepHealthHR() }
    val getVaccinationStepElderly by lazy { _repository.getVaccinationStepElderly() }
    val getVaccinationStepPublicOfficer by lazy { _repository.getVaccinationStepPublicOfficer() }
    val getVaccinationCoverage by lazy { _repository.getVaccinationCoverage() }
}