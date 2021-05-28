package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaccineCakupan")
data class VaccinationCakupanEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="id")
        val id: Int,
        @ColumnInfo(name="sdmKesehatanVaksinasi2")
        val sdmKesehatanVaksinasi2: String? = null,
        @ColumnInfo(name="sdmKesehatanVaksinasi1")
        val sdmKesehatanVaksinasi1: String? = null,
        @ColumnInfo(name="lansiaVaksinasi1")
        val lansiaVaksinasi1: String? = null,
        @ColumnInfo(name="petugasPublikVaksinasi2")
        val petugasPublikVaksinasi2: String? = null,
        @ColumnInfo(name="petugasPublikVaksinasi1")
        val petugasPublikVaksinasi1: String? = null,
        @ColumnInfo(name="vaksinasi2")
        val vaksinasi2: String? = null,
        @ColumnInfo(name="vaksinasi1")
        val vaksinasi1: String? = null,
        @ColumnInfo(name="lansiaVaksinasi2")
        val lansiaVaksinasi2: String? = null
)