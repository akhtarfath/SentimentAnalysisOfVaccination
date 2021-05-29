package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaccineSDM")
data class VaccinationHealthHREntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "vaccinated1")
        val vaccinated1: Int? = null,

        @ColumnInfo(name = "totalVaccination2")
        val totalVaccination2: Int? = null,

        @ColumnInfo(name = "totalVaccination1")
        val totalVaccination1: Int? = null,

        @ColumnInfo(name = "vaccinated2")
        val vaccinated2: Int? = null,

        @ColumnInfo(name = "delayedVaccination2")
        val delayedVaccination2: Int? = null,

        @ColumnInfo(name = "delayedVaccination1")
        val delayedVaccination1: Int? = null
)