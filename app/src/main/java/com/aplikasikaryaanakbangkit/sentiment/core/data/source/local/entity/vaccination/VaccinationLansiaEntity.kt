package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="vaccineLansia")
data class VaccinationLansiaEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="id")
        val id: Int,
        @ColumnInfo(name="sudahVaksin1L")
        val sudahVaksin1: Int? = null,
        @ColumnInfo(name="totalVaksinasi2L")
        val totalVaksinasi2: Int? = null,
        @ColumnInfo(name="totalVaksinasi1L")
        val totalVaksinasi1: Int? = null,
        @ColumnInfo(name="sudahVaksin2L")
        val sudahVaksin2: Int? = null,
        @ColumnInfo(name="tertundaVaksin2L")
        val tertundaVaksin2: Int? = null,
        @ColumnInfo(name="tertundaVaksin1L")
        val tertundaVaksin1: Int? = null
)