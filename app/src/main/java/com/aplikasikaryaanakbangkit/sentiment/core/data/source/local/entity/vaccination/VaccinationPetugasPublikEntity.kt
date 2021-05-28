package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class VaccinationPetugasPublikEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="id")
        val id: Int,
        @ColumnInfo(name="sudahVaksin1PP")
        val sudahVaksin1PP: Int? = null,
        @ColumnInfo(name="totalVaksinasi2PP")
        val totalVaksinasi2PP: Int? = null,
        @ColumnInfo(name="totalVaksinasi1PP")
        val totalVaksinasi1PP: Int? = null,
        @ColumnInfo(name="sudahVaksin2PP")
        val sudahVaksin2PP: Int? = null,
        @ColumnInfo(name="tertundaVaksin2PP")
        val tertundaVaksin2PP: Int? = null,
        @ColumnInfo(name="tertundaVaksin1PP")
        val tertundaVaksin1PP: Int? = null
)