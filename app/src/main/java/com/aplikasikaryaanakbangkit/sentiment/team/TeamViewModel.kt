package com.aplikasikaryaanakbangkit.sentiment.team

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository

class TeamViewModel(
        _repository: SAVRepository
) : ViewModel() {

    val getDataTeams = _repository.getAllTeams()
}