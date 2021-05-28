package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.teams

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamsResponse(
    val id: String,
    val name: String,
    val urlPicture: String
) : Parcelable