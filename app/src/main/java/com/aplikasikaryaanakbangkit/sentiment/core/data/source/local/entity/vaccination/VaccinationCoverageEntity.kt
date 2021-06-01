package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaccineCoverage")
data class VaccinationCoverageEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "healthHRVaccination2")
        val healthHRVaccination2: String? = null,

        @ColumnInfo(name = "healthHRVaccination1")
        val healthHRVaccination1: String? = null,

        @ColumnInfo(name = "elderlyVaccination1")
        val elderlyVaccination1: String? = null,

        @ColumnInfo(name = "publicOfficerVaccination2")
        val publicOfficerVaccination2: String? = null,

        @ColumnInfo(name = "publicOfficerVaccination1")
        val publicOfficerVaccination1: String? = null,

        @ColumnInfo(name = "vaccination2")
        val vaccination2: String? = null,

        @ColumnInfo(name = "vaccination1")
        val vaccination1: String? = null,

        @ColumnInfo(name = "elderlyVaccination2")
        val elderlyVaccination2: String? = null
)