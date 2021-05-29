package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet

import com.google.gson.annotations.SerializedName

data class ReferencedTweetResponse(
        @field:SerializedName("type")
        val type: String? = null,

        @field:SerializedName("id")
        val id: String? = null
)