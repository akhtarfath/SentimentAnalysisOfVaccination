package com.aplikasikaryaanakbangkit.sentiment.core.di

import android.content.Context
import com.aplikasikaryaanakbangkit.sentiment.core.data.NewsRepository
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.LocalDataSource
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room.SentimentVaccinationDatabase
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.RemoteDataSource
import com.aplikasikaryaanakbangkit.sentiment.core.utils.AppExecutors
import com.aplikasikaryaanakbangkit.sentiment.core.utils.JsonHelper

object Injection {

    fun newsRepository(context: Context): NewsRepository {
        val database = SentimentVaccinationDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource =
            LocalDataSource.getInstance(database.newsDao())

        val appExecutors = AppExecutors()

        return NewsRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
