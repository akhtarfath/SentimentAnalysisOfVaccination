package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SourceResponse(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: String

) : Parcelable