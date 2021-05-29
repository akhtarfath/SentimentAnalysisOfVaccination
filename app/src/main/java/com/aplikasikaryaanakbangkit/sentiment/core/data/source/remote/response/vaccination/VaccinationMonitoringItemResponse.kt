package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination

import com.google.gson.annotations.SerializedName

data class VaccinationMonitoringItemResponse(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("sasaran_vaksinasi_petugas_publik")
    val vaccinationTargetPublicOfficer: Int? = null,

    @field:SerializedName("tahapan_vaksinasi")
    val vaccinationStep: VaccinationSteoResponse? = null,

    @field:SerializedName("vaksinasi2")
    val vaccination2: Int? = null,

    @field:SerializedName("vaksinasi1")
    val vaccination1: Int? = null,

    @field:SerializedName("sasaran_vaksinasi_sdmk")
    val targetVaccinationHealthHR: Int? = null,

    @field:SerializedName("sasaran_vaksinasi_lansia")
    val targetVaccinationElderly: Int? = null,

    @field:SerializedName("cakupan")
    val coverage: VaccinationCoverageResponse? = null,

    @field:SerializedName("total_sasaran_vaksinasi")
    val totalVaccinationTarget: Int? = null
)