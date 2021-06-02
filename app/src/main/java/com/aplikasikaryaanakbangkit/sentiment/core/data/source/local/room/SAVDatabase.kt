package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid.GlobalCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid.IDCovidItemEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.sentiment.SentimentEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.teams.TeamsEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.DataItemTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.UserItemsTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination.*

@Database(
    entities = [
        ArticleCovidEntity::class,
        ArticleVaccinesEntity::class,
        TeamsEntity::class,
        DataItemTweetEntity::class,
        UserItemsTweetEntity::class,
        GlobalCovidEntity::class,
        IDCovidItemEntity::class,
        VaccinationCoverageEntity::class,
        VaccinationElderlyEntity::class,
        VaccinationMonitoringItemEntity::class,
        VaccinationPublicOfficerEntity::class,
        VaccinationHealthHREntity::class,
        SentimentEntity::class
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