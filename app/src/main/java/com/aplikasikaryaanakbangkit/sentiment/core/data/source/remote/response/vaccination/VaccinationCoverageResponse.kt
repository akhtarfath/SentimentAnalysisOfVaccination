package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination

import com.google.gson.annotations.SerializedName

data class VaccinationCoverageResponse(
        @field:SerializedName("sdm_kesehatan_vaksinasi2")
        val vaccinationHealthHR2: String? = null,

        @field:SerializedName("sdm_kesehatan_vaksinasi1")
        val vaccinationHealthHR1: String? = null,

        @field:SerializedName("lansia_vaksinasi1")
        val vaccinationElderly1: String? = null,

        @field:SerializedName("petugas_publik_vaksinasi2")
        val vaccinationPublicOfficer2: String? = null,

        @field:SerializedName("petugas_publik_vaksinasi1")
        val vaccinationPublicOfficer1: String? = null,

        @field:SerializedName("vaksinasi2")
        val vaccination2: String? = null,

        @field:SerializedName("vaksinasi1")
        val vaccination1: String? = null,

        @field:SerializedName("lansia_vaksinasi2")
        val vaccinationElderly2: String? = null
)