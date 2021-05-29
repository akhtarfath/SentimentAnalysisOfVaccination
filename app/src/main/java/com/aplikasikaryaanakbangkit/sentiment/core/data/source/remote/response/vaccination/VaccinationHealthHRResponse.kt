package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination

import com.google.gson.annotations.SerializedName

data class VaccinationHealthHRResponse(
        @field:SerializedName("sudah_vaksin1")
        val vaccinated1: Int? = null,

        @field:SerializedName("total_vaksinasi2")
        val totalVaccination2: Int? = null,

        @field:SerializedName("total_vaksinasi1")
        val totalVaccination1: Int? = null,

        @field:SerializedName("sudah_vaksin2")
        val vaccinated2: Int? = null,

        @field:SerializedName("tertunda_vaksin2")
        val delayedVaccination2: Int? = null,

        @field:SerializedName("tertunda_vaksin1")
        val delayedVaccination1: Int? = null
)