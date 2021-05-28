package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.news

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticlesItemResponse(

    @field:SerializedName("publishedAt")
    val publishedAt: String,

    @field:SerializedName("author")
    val author: String?,

    @field:SerializedName("urlToImage")
    val urlToImage: String?,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("source")
    val sourceResponse: SourceResponse,

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("content")
    val content: String?

) : Parcelable