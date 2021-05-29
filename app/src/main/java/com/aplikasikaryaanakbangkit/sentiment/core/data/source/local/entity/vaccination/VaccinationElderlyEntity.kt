package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="vaccineElderly")
data class VaccinationElderlyEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="id")
        val id: Int,

        @ColumnInfo(name="vaccinated1")
        val vaccinated1: Int? = null,

        @ColumnInfo(name="totalVaccination2")
        val totalVaccination2: Int? = null,

        @ColumnInfo(name="totalVaccination1")
        val totalVaccination1: Int? = null,

        @ColumnInfo(name="vaccinated2")
        val vaccinated2: Int? = null,

        @ColumnInfo(name="delayedVaccine2")
        val delayedVaccine2: Int? = null,

        @ColumnInfo(name="delayedVaccine1")
        val delayedVaccine1: Int? = null
)