package com.aplikasikaryaanakbangkit.sentiment.di

import android.content.Context
import com.aplikasikaryaanakbangkit.sentiment.data.source.NewsRepository
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.LocalDataSource
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.room.NewsDatabase
import com.aplikasikaryaanakbangkit.sentiment.data.source.remote.RemoteDataSource
import com.aplikasikaryaanakbangkit.sentiment.data.source.remote.api.NewsService
import com.aplikasikaryaanakbangkit.sentiment.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val database = NewsDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(NewsService.create())
        val localDataSource = LocalDataSource.getInstance(database.newsDao())
        val appExecutors = AppExecutors()
        return NewsRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}