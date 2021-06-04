package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.sentiment

import com.google.gson.annotations.SerializedName

data class TextTweet(
        @SerializedName("text_twitter")
        val text_twitter: String
)