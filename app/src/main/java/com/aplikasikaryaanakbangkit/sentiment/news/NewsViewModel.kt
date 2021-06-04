package com.aplikasikaryaanakbangkit.sentiment.news

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository

class NewsViewModel(
        _newsRepository: SAVRepository
) : ViewModel() {

    val newsCovidHeadline by lazy { _newsRepository.getCovidHeadlines() }
    val newsVaccine by lazy { _newsRepository.getVaccineNews() }
}