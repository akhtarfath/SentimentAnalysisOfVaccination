package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination

import com.google.gson.annotations.SerializedName

data class VaccinationMonitoringItemResponse(
    @field:SerializedName("date")
    val date: String? = null,
    @field:SerializedName("sasaran_vaksinasi_petugas_publik")
    val sasaranVaksinasiPetugasPublik: Int? = null,
    @field:SerializedName("tahapan_vaksinasi")
    val tahapanVaksinasi: VaccinationTahapanResponse? = null,
    @field:SerializedName("vaksinasi2")
    val vaksinasi2: Int? = null,
    @field:SerializedName("vaksinasi1")
    val vaksinasi1: Int? = null,
    @field:SerializedName("sasaran_vaksinasi_sdmk")
    val sasaranVaksinasiSdmk: Int? = null,
    @field:SerializedName("sasaran_vaksinasi_lansia")
    val sasaranVaksinasiLansia: Int? = null,
    @field:SerializedName("cakupan")
    val cakupan: VaccinationCakupanResponse? = null,
    @field:SerializedName("total_sasaran_vaksinasi")
    val totalSasaranVaksinasi: Int? = null
)