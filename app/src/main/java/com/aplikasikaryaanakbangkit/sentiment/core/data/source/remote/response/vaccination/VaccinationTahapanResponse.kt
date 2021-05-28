package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination

import com.google.gson.annotations.SerializedName

data class VaccinationTahapanResponse(
    @field:SerializedName("petugas_publik")
    val petugasPublik: VaccinationPetugasPublikResponse? = null,
    @field:SerializedName("lansia")
    val lansia: VaccinationLansiaResponse? = null,
    @field:SerializedName("sdm_kesehatan")
    val sdmKesehatan: VaccinationSdmKesehatanResponse? = null
)