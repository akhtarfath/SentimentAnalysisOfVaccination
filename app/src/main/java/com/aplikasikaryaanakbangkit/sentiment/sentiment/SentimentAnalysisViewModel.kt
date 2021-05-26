package com.aplikasikaryaanakbangkit.sentiment.sentiment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SentimentRepository
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TweetEntity

class SentimentAnalysisViewModel(
    private val _repository: SentimentRepository
) : ViewModel() {

    fun getTweet(): LiveData<List<TweetEntity>> = _repository.getAllTweet()

}