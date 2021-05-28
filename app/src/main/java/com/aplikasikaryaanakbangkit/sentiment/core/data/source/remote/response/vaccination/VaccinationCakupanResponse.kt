package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination

import com.google.gson.annotations.SerializedName

data class VaccinationCakupanResponse(
        @field:SerializedName("sdm_kesehatan_vaksinasi2")
        val sdmKesehatanVaksinasi2: String? = null,
        @field:SerializedName("sdm_kesehatan_vaksinasi1")
        val sdmKesehatanVaksinasi1: String? = null,
        @field:SerializedName("lansia_vaksinasi1")
        val lansiaVaksinasi1: String? = null,
        @field:SerializedName("petugas_publik_vaksinasi2")
        val petugasPublikVaksinasi2: String? = null,
        @field:SerializedName("petugas_publik_vaksinasi1")
        val petugasPublikVaksinasi1: String? = null,
        @field:SerializedName("vaksinasi2")
        val vaksinasi2: String? = null,
        @field:SerializedName("vaksinasi1")
        val vaksinasi1: String? = null,
        @field:SerializedName("lansia_vaksinasi2")
        val lansiaVaksinasi2: String? = null
)