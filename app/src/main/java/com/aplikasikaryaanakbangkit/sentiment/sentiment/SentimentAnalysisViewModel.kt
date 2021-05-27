package com.aplikasikaryaanakbangkit.sentiment.sentiment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.DataItemTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.UserItemsTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Resource

class SentimentAnalysisViewModel(
    private val _repository: SAVRepository
) : ViewModel() {

    fun getTweet(): LiveData<List<TweetEntity>> = _repository.getAllTweet()

    fun getPost(): LiveData<Resource<List<DataItemTweetEntity>>> = _repository.getAllPost()
    fun getProfile(): LiveData<Resource<List<UserItemsTweetEntity>>> = _repository.getAllProfile()
    // fun getMetrics(): LiveData<Resource<List<PublicMetricsTweetEntity>>> = _repository.()

}