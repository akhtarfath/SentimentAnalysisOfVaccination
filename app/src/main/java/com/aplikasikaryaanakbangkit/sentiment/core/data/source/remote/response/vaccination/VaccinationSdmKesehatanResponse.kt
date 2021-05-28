package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination

import com.google.gson.annotations.SerializedName

data class VaccinationSdmKesehatanResponse(
    @field:SerializedName("sudah_vaksin1")
    val sudahVaksin1: Int? = null,
    @field:SerializedName("total_vaksinasi2")
    val totalVaksinasi2: Int? = null,
    @field:SerializedName("total_vaksinasi1")
    val totalVaksinasi1: Int? = null,
    @field:SerializedName("sudah_vaksin2")
    val sudahVaksin2: Int? = null,
    @field:SerializedName("tertunda_vaksin2")
    val tertundaVaksin2: Int? = null,
    @field:SerializedName("tertunda_vaksin1")
    val tertundaVaksin1: Int? = null
)