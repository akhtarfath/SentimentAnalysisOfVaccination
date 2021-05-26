package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tweetProfile")
data class UserItemsTweetEntity(

        @PrimaryKey
        @ColumnInfo(name ="authorId")
        var authorId: String,

        @ColumnInfo(name ="name")
        var name: String,

        @ColumnInfo(name ="profile_image_url")
        var profileImageUrl: String,

        @ColumnInfo(name ="username")
        var username: String
)