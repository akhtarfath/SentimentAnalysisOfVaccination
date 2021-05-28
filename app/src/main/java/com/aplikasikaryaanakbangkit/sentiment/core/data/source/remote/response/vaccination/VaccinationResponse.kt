package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination

import com.google.gson.annotations.SerializedName

data class VaccinationResponse(
    @field:SerializedName("last_updated")
    val lastUpdated: String? = null,
    @field:SerializedName("monitoring")
    val monitoring: List<VaccinationMonitoringItemResponse?>? = null
)

