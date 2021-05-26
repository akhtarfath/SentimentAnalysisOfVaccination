package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity

data class TweetEntity(
    val authorId: String,
    val name: String,
    val imageUrl: String,
    val username: String,
    val date: String,
    val text: String,
    val likeCount: Int,
    val quoteCount: Int,
    val replyCount: Int,
    val retweetCount: Int,
)