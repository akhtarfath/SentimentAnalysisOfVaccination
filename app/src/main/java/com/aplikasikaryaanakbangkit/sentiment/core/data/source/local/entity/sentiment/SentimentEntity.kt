package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.sentiment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sentimentAnalysis")
data class SentimentEntity(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "textTweet")
        val textTweet: String,

        @ColumnInfo(name = "result")
        val result: String

)