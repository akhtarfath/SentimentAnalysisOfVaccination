package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet

import com.google.gson.annotations.SerializedName

data class IncludeUserTweetResponse(
    @field:SerializedName("users")
    val users: List<UserItemsTweetResponse?>? = null
)
