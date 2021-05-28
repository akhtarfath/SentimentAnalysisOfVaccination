package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "confirmGlobalCovid")
data class ConfirmedGlobalCovidEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "urlConfirmed")
    val detail: String? = null,
    @ColumnInfo(name = "valueConfirmed")
    val value: Int? = null
)