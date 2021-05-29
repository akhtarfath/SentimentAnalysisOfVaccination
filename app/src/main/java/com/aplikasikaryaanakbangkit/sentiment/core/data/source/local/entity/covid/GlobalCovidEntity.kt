package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "globalCovid")
data class GlobalCovidEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "deathGlobal")
        val deathGlobal: Int? = null,

        @ColumnInfo(name = "confirmedGlobal")
        val confirmedGlobal: Int? = null,

        @ColumnInfo(name = "recoveredGlobal")
        val recoveredGlobal: Int? = null
)







