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
abstract class SAVDatabase : RoomDatabase() {

    abstract fun sentimentDao(): SAVDao

    companion object {

        @Volatile
        private var INSTANCE: SAVDatabase? = null

        fun getInstance(context: Context): SAVDatabase = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                SAVDatabase::class.java,
                "sav_database.db"
            ).build().apply {
                INSTANCE = this
            }
        }
    }
}