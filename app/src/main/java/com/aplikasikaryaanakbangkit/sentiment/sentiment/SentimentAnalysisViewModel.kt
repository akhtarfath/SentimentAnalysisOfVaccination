package com.aplikasikaryaanakbangkit.sentiment.sentiment

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository

class SentimentAnalysisViewModel(
    private val _repository: SAVRepository
) : ViewModel() {

    fun getTweet() = _repository.getAllTweet()

    fun getPost() = _repository.getAllPost()

    fun getProfile() = _repository.getAllProfile()
}