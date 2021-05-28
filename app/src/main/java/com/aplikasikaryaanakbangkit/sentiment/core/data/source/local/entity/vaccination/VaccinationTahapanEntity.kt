package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination

data class VaccinationTahapanEntity(
        //SDM
        val sudahVaksin1SDM: Int? = null,
        val totalVaksinasi2SDM: Int? = null,
        val totalVaksinasi1SDM: Int? = null,
        val sudahVaksin2SDM: Int? = null,
        val tertundaVaksin2SDM: Int? = null,
        val tertundaVaksin1SDM: Int? = null,

        //Petugas Publik
        val sudahVaksin1PP: Int? = null,
        val totalVaksinasi2PP: Int? = null,
        val totalVaksinasi1PP: Int? = null,
        val sudahVaksin2PP: Int? = null,
        val tertundaVaksin2PP: Int? = null,
        val tertundaVaksin1PP: Int? = null,

        //Lansian
        val sudahVaksin1L: Int? = null,
        val totalVaksinasi2L: Int? = null,
        val totalVaksinasi1L: Int? = null,
        val sudahVaksin2L: Int? = null,
        val tertundaVaksin2L: Int? = null,
        val tertundaVaksin1L: Int? = null
)