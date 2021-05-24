package com.aplikasikaryaanakbangkit.sentiment.sentiment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SentimentAnalysisViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Sentiment Analysis page"
    }
    val text: LiveData<String> = _text
}