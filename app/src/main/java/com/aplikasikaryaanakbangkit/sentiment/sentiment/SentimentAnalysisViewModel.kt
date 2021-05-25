package com.aplikasikaryaanakbangkit.sentiment.sentiment

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SentimentRepository

class SentimentAnalysisViewModel(
        _repository: SentimentRepository
) : ViewModel() {

    val allTweets = _repository.getAllTweet()
}