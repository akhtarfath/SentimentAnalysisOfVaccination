package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deathGlobalCovid")
data class DeathGlobalCovidEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "urlDeath")
    val detail: String? = null,
    @ColumnInfo(name = "valueDeath")
    val value: Int? = null
)