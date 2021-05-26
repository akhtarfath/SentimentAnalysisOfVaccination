package com.aplikasikaryaanakbangkit.sentiment.news

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository

class NewsViewModel(
    _newsRepository: SAVRepository
) : ViewModel() {

    val newsHeadline = _newsRepository.getCovidHeadlines()

    val newsLatest = _newsRepository.getVaccineNews()
}