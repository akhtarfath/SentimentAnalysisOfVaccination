package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.sentiment

data class SentimentResponse(
        val id: Int,
        val textTweet: String,
        val result: String
)