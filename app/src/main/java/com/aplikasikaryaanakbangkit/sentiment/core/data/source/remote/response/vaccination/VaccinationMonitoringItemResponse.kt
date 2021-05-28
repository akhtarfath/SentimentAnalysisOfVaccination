package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination

data class VaccinationMonitoringItemResponse(
        val date: String? = null,
        val sasaranVaksinasiPetugasPublik: Int? = null,
        val tahapanVaksinasi: VaccinationTahapanResponse? = null,
        val vaksinasi2: Int? = null,
        val vaksinasi1: Int? = null,
        val sasaranVaksinasiSdmk: Int? = null,
        val sasaranVaksinasiLansia: Int? = null,
        val cakupan: VaccinationCakupanResponse? = null,
        val totalSasaranVaksinasi: Int? = null
)