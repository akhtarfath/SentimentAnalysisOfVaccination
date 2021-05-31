package com.aplikasikaryaanakbangkit.sentiment.sentiment

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.sentiment.TextTweet

class SentimentAnalysisViewModel(
        private val _repository: SAVRepository
) : ViewModel() {

    //tweet
    fun getTweet() = _repository.getAllTweet()
    fun getPost() = _repository.getAllPost()
    fun getProfile() = _repository.getAllProfile()

    //sentiment
    fun getAnalysis(tweet: TextTweet) = _repository.getAnalysis(tweet)
}