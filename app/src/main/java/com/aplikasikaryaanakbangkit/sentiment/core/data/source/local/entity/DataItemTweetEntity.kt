package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity

import androidx.room.*

@Entity(tableName = "tweetPost",
        primaryKeys = ["id", "authorId"],
        foreignKeys = [ForeignKey(entity = UserItemsTweetEntity::class,
                parentColumns = ["authorId"],
                childColumns = ["authorId"])],
        indices = [Index(value = ["id"]),
        Index(value = ["authorId"])]
        )
data class DataItemTweetEntity(

        @ColumnInfo(name ="id")
        var id: String,

        @ColumnInfo(name ="created_at")
        var createdAt: String,

        @ColumnInfo(name ="text")
        var text: String,

        @ColumnInfo(name ="authorId")
        var authorId: String
){
        @Embedded
        var publicMetrics: PublicMetricsTweetEntity? = null
}