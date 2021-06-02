package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.sentiment

import com.google.gson.annotations.SerializedName

data class SentimentResponse(
    @field:SerializedName("result")
    val result: String
)