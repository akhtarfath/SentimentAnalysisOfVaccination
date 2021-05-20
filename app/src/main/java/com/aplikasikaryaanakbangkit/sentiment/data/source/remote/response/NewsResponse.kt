package com.aplikasikaryaanakbangkit.sentiment.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsResponse(
    @field:SerializedName("totalResults")
    val totalResults: Int,

    @field:SerializedName("articles")
    val articles: List<ArticlesItemResponse>,

    @field:SerializedName("status")
    val status: String
) : Parcelable
