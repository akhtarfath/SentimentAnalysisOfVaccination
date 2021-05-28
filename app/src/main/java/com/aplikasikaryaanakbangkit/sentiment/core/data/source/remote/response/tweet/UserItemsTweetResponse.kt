package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet

import com.google.gson.annotations.SerializedName

data class UserItemsTweetResponse(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("profile_image_url")
    val profileImageUrl: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)