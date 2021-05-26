package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity

import androidx.room.*

@Entity(
    tableName = "tweetPost",
    primaryKeys = ["id", "authorId"],
    foreignKeys = [ForeignKey(
        entity = UserItemsTweetEntity::class,
        parentColumns = ["authorId"],
        childColumns = ["authorId"]
    )],
    indices = [Index(value = ["id"]),
        Index(value = ["authorId"])]
)
data class DataItemTweetEntity(

    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "created_at")
    val createdAt: String,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "authorId")
    val authorId: String
) {
    @Embedded
    var publicMetrics: PublicMetricsTweetEntity? = null
}