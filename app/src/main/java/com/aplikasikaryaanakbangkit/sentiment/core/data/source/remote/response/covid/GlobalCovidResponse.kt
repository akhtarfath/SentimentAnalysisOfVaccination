package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.covid

import com.google.gson.annotations.SerializedName

data class GlobalCovidResponse(
    @field:SerializedName("recovered")
    val recovered: RecoveredGlobalCovidResponse? = null,
    @field:SerializedName("confirmed")
    val confirmed: ConfirmedGlobalCovidResponse? = null,
    @field:SerializedName("deaths")
    val deaths: DeathGlobalCovidResponse? = null
)





