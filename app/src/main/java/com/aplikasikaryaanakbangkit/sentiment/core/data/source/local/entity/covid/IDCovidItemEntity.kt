package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "idCovid")
data class IDCovidItemEntity(

    @PrimaryKey
    @ColumnInfo(name = "lastUpdate")
    val lastUpdate: Long? = null,

    @ColumnInfo(name = "confirmed")
    val confirmed: Int? = null,

    @ColumnInfo(name = "recovered")
    val recovered: Int? = null,

    @ColumnInfo(name = "deaths")
    val deaths: Int? = null
)