package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity

data class TweetEntity(
		var authorId:String,
		var name: String,
		var imageUrl: String,
		var username: String,
		var date: String,
		var text: String,
		var likeCount: Int,
		var quoteCount: Int,
		var replyCount: Int,
		var retweetCount: Int,
)