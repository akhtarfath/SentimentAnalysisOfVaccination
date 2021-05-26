package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tweetProfile")
data class UserItemsTweetEntity(

        @PrimaryKey
        @ColumnInfo(name ="authorId")
        val authorId: String,

        @ColumnInfo(name ="name")
        val name: String,

        @ColumnInfo(name ="profile_image_url")
        val profileImageUrl: String,

        @ColumnInfo(name ="username")
        val username: String
)