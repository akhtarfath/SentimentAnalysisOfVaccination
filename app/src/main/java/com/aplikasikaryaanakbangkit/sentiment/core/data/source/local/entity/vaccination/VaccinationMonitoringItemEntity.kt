package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaccineMonitoring")
data class VaccinationMonitoringItemEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="id")
        val id: Int,
        @ColumnInfo(name="date")
        val date: String? = null,
        @ColumnInfo(name="sasaranVaksinasiPetugasPublik")
        val sasaranVaksinasiPetugasPublik: Int? = null,
        @ColumnInfo(name="vaksinasi2")
        val vaksinasi2: Int? = null,
        @ColumnInfo(name="vaksinasi1")
        val vaksinasi1: Int? = null,
        @ColumnInfo(name="sasaranVaksinasiSdmk")
        val sasaranVaksinasiSdmk: Int? = null,
        @ColumnInfo(name="sasaranVaksinasiLansia")
        val sasaranVaksinasiLansia: Int? = null,
        @ColumnInfo(name="totalSasaranVaksinasi")
        val totalSasaranVaksinasi: Int? = null
)