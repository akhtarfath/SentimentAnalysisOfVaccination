package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.*

@Database(
    entities = [
        ArticleCovidEntity::class,
        ArticleVaccinesEntity::class,
        TeamsEntity::class,
        DataItemTweetEntity::class,
        UserItemsTweetEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SentimentVaccinationDatabase : RoomDatabase() {

    abstract fun sentimentDao(): SentimentDao

    companion object {

        @Volatile
        private var INSTANCE: SentimentVaccinationDatabase? = null

        fun getInstance(context: Context): SentimentVaccinationDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    SentimentVaccinationDatabase::class.java,
                    "sav_database.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}