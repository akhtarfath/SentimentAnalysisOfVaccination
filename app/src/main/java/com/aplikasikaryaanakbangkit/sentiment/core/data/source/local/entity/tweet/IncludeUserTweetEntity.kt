package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet

import androidx.room.Embedded

data class IncludeUserTweetEntity(
    @Embedded
    val users: List<UserItemsTweetEntity?>? = null
)
