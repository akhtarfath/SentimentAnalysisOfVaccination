package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet

import com.google.gson.annotations.SerializedName

data class DataItemTweetResponse(

        @field:SerializedName("public_metrics")
        val publicMetrics: PublicMetricsTweetResponse? = null,

        @field:SerializedName("referenced_tweets")
        val referencedTweets: List<ReferencedTweetResponse>? = null,

        @field:SerializedName("created_at")
        val createdAt: String? = null,

        @field:SerializedName("text")
        val text: String? = null,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("author_id")
        val authorId: String? = null
)