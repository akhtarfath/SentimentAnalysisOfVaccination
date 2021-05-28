package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class VaccinationSdmKesehatanEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="id")
        val id: Int,
        @ColumnInfo(name="sudahVaksin1SDM")
        val sudahVaksin1SDM: Int? = null,
        @ColumnInfo(name="totalVaksinasi2SDM")
        val totalVaksinasi2SDM: Int? = null,
        @ColumnInfo(name="totalVaksinasi1SDM")
        val totalVaksinasi1SDM: Int? = null,
        @ColumnInfo(name="sudahVaksin2SDM")
        val sudahVaksin2SDM: Int? = null,
        @ColumnInfo(name="tertundaVaksin2SDM")
        val tertundaVaksin2SDM: Int? = null,
        @ColumnInfo(name="tertundaVaksin1SDM")
        val tertundaVaksin1SDM: Int? = null
)