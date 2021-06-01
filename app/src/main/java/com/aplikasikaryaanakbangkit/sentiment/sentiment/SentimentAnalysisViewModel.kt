package com.aplikasikaryaanakbangkit.sentiment.sentiment

import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.sentiment.TextTweet

class SentimentAnalysisViewModel(
    private val _repository: SAVRepository
) : ViewModel() {

    //tweet
    val tweet by lazy { _repository.getAllTweet() }
    val post by lazy { _repository.getAllPost() }
    val profile by lazy { _repository.getAllProfile() }

    //sentiment
    fun getAnalysis(tweet: TextTweet) = _repository.getAnalysis(tweet)

    val neutralCount by lazy { _repository.getNeutralAnalysis() }
    val positiveCount by lazy { _repository.getPositiveAnalysis() }
    val negativeCount by lazy { _repository.getNegativeAnalysis() }
    val allSentimentCount by lazy { _repository.getAllAnalysis() }


}