package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PublicMetricsTweetResponse(

        @field:SerializedName("like_count")
        val likeCount: Int? = null,

        @field:SerializedName("reply_count")
        val replyCount: Int? = null,

        @field:SerializedName("quote_count")
        val quoteCount: Int? = null,

        @field:SerializedName("retweet_count")
        val retweetCount: Int? = null
)