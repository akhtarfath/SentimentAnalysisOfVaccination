package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TeamsEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TweetEntity

@Database(
    entities = [
        ArticleCovidEntity::class,
        ArticleVaccinesEntity::class,
        TeamsEntity::class,
        TweetEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class SAVDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {

        @Volatile
        private var INSTANCE: SAVDatabase? = null

        fun getInstance(context: Context): SAVDatabase =
            INSTANCE ?: synchronized(this) {
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