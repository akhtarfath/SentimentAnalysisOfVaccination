package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tweet")
data class TweetEntity (
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String
        )