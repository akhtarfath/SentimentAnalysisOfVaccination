package com.aplikasikaryaanakbangkit.sentiment.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.room.NewsDao

class LocalDataSource private constructor(private val mNewsDao: NewsDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(newsDao: NewsDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(newsDao).apply {
                INSTANCE = this
            }
    }

    fun getCovidArticles(): DataSource.Factory<Int, ArticleCovidEntity> =
        mNewsDao.getCovidArticles()

    fun insertCovidArticles(article: List<ArticleCovidEntity>) =
        mNewsDao.insertCovidArticles(article)

    fun getCovidArticleByUrl(url: String): LiveData<ArticleCovidEntity> =
        mNewsDao.getCovidArticleByUrl(url)

    fun getVaccineArticles(): DataSource.Factory<Int, ArticleVaccinesEntity> =
        mNewsDao.getVaccineArticles()

    fun insertVaccineArticles(article: List<ArticleVaccinesEntity>) =
        mNewsDao.insertVaccineArticles(article)

    fun getVaccineArticleByUrl(url: String): LiveData<ArticleVaccinesEntity> =
        mNewsDao.getVaccineArticleByUrl(url)
}