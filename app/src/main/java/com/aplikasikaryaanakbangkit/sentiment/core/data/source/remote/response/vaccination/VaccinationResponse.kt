package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination

data class VaccinationResponse(
	val lastUpdated: String? = null,
	val monitoring: List<VaccinationMonitoringItemResponse?>? = null
)

