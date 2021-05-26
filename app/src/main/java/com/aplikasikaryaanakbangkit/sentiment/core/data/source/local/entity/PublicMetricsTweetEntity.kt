package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity

import androidx.room.ColumnInfo

data class PublicMetricsTweetEntity(

    @ColumnInfo(name = "likeCount")
    val likeCount: Int? = null,

    @ColumnInfo(name = "replyCount")
    val replyCount: Int? = null,

    @ColumnInfo(name = "quoteCount")
    val quoteCount: Int? = null,

    @ColumnInfo(name = "retweetCount")
    val retweetCount: Int? = null
)