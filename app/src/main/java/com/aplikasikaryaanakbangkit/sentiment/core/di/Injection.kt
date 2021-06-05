package com.aplikasikaryaanakbangkit.sentiment.core.di

import android.content.Context
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.LocalDataSource
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room.SAVDatabase
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.RemoteDataSource
import com.aplikasikaryaanakbangkit.sentiment.core.utils.AppExecutors
import com.aplikasikaryaanakbangkit.sentiment.core.utils.JsonHelper

object Injection {

    fun savRepository(context: Context): SAVRepository {
        val database = SAVDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource =
                LocalDataSource.getInstance(database.sentimentDao())

        val appExecutors = AppExecutors()

        return SAVRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
