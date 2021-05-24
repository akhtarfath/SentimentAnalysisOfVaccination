package com.aplikasikaryaanakbangkit.sentiment.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SentimentRepository

class TeamViewModel(
    _repository: SentimentRepository
) : ViewModel() {

    val getDataTeams = _repository.getAllTeams()
}