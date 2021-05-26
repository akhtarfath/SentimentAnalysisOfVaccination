package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity

import androidx.room.ColumnInfo

data class PublicMetricsTweetEntity(

        @ColumnInfo(name ="likeCount")
        var likeCount: Int? = null,

        @ColumnInfo(name ="replyCount")
        var replyCount: Int? = null,

        @ColumnInfo(name ="quoteCount")
        var quoteCount: Int? = null,

        @ColumnInfo(name ="retweetCount")
        var retweetCount: Int? = null
)