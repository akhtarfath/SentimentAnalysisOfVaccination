package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.covid

import com.google.gson.annotations.SerializedName

data class RecoveredGlobalCovidResponse(
        @field:SerializedName("detail")
        val detail: String? = null,
        @field:SerializedName("value")
        val value: Int? = null
)