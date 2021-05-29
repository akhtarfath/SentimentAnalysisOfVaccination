package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination

import com.google.gson.annotations.SerializedName

data class VaccinationSteoResponse(
        @field:SerializedName("petugas_publik")
    val publicOfficer: VaccinationPublicOfficerResponse? = null,
        @field:SerializedName("lansia")
    val elderly: VaccinationElderlyResponse? = null,
        @field:SerializedName("sdm_kesehatan")
    val healthHR: VaccinationHealthHRResponse? = null
)