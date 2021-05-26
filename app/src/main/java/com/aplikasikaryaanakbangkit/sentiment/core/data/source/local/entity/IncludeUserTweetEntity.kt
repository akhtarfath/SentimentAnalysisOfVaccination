package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity

import androidx.room.Embedded

data class IncludeUserTweetEntity(
    @Embedded
    val users: List<UserItemsTweetEntity?>? = null
)
