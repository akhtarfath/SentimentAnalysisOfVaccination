package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet

import com.google.gson.annotations.SerializedName

data class TweetResponse(

        @field:SerializedName("data")
        val data: List<DataItemTweetResponse?>? = null,

        @field:SerializedName("includes")
        val includes: IncludeUserTweetResponse? = null
)