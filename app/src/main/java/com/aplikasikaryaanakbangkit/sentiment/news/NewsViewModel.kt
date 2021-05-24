package com.aplikasikaryaanakbangkit.sentiment.news

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.NewsRepository

class NewsViewModel(
    _newsRepository: NewsRepository
) : ViewModel() {

    val newsHeadline = _newsRepository.getCovidHeadlines()

    val newsLatest = _newsRepository.getVaccineNews()
}