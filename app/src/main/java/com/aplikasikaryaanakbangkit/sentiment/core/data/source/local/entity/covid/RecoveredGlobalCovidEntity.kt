package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recoveredGlobalEntity")
data class RecoveredGlobalCovidEntity(

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name="urlRecovered")
        val detail: String? = null,

        @ColumnInfo(name="valueRecovered")
        val value: Int? = null
)