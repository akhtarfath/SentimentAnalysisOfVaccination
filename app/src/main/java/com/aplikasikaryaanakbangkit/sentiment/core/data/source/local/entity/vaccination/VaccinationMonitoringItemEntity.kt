package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaccineMonitoring")
data class VaccinationMonitoringItemEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "date")
        val date: String? = null,

        @ColumnInfo(name = "vaccinationTargetPublicOfficer")
        val vaccinationTargetPublicOfficer: Int? = null,

        @ColumnInfo(name = "vaccination2")
        val vaccination2: Int? = null,

        @ColumnInfo(name = "vaccination1")
        val vaccination1: Int? = null,

        @ColumnInfo(name = "vaccinationTargetHealthHR")
        val vaccinationTargetHealthHR: Int? = null,

        @ColumnInfo(name = "vaccinationTargetElderly")
        val vaccinationTargetElderly: Int? = null,

        @ColumnInfo(name = "totalTargetVaccination")
        val totalTargetVaccination: Int? = null
)