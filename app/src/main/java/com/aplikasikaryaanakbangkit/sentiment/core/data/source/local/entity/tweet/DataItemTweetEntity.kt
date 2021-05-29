package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet

import androidx.room.*

@Entity(
    tableName = "tweetPost"
)
data class DataItemTweetEntity(

        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,

        @ColumnInfo(name = "created_at")
        val createdAt: String,

        @ColumnInfo(name = "text")
        val text: String,

        @ColumnInfo(name = "authorId")
        val authorId: String,

        @ColumnInfo(name = "likeCount")
        val likeCount: Int? = null,

        @ColumnInfo(name = "replyCount")
        val replyCount: Int? = null,

        @ColumnInfo(name = "quoteCount")
        val quoteCount: Int? = null,

        @ColumnInfo(name = "retweetCount")
        val retweetCount: Int? = null
)