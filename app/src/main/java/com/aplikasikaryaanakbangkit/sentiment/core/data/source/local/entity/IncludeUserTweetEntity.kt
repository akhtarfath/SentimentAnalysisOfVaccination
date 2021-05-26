package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity

import androidx.room.Embedded

data class IncludeUserTweetEntity(
        @Embedded
        var users: List<UserItemsTweetEntity?>? = null
)
