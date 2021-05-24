package com.aplikasikaryaanakbangkit.sentiment.news

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SentimentRepository

class NewsViewModel(
    _newsRepository: SentimentRepository
) : ViewModel() {

    val newsHeadline = _newsRepository.getCovidHeadlines()

    val newsLatest = _newsRepository.getVaccineNews()
}