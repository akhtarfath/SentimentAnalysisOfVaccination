package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination

data class VaccinationTahapanResponse(
        val petugasPublik: VaccinationPetugasPublikResponse? = null,
        val lansia: VaccinationLansiaResponse? = null,
        val sdmKesehatan: VaccinationSdmKesehatanResponse? = null
)