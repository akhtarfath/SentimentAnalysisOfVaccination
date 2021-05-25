package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TeamsEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room.NewsDao

class LocalDataSource private constructor(
    private val newsDao: NewsDao
) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(
            newsDao: NewsDao
        ): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(newsDao)
            }
    }

    fun getCovidArticles(): DataSource.Factory<Int, ArticleCovidEntity> =
        newsDao.getCovidArticles()

    fun insertCovidArticles(article: List<ArticleCovidEntity>) =
        newsDao.insertCovidArticles(article)

    fun getCovidArticleByUrl(url: String): LiveData<ArticleCovidEntity> =
        newsDao.getCovidArticleByUrl(url)

    fun getVaccineArticles(): DataSource.Factory<Int, ArticleVaccinesEntity> =
        newsDao.getVaccineArticles()

    fun insertVaccineArticles(article: List<ArticleVaccinesEntity>) =
        newsDao.insertVaccineArticles(article)

    fun getVaccineArticleByUrl(url: String): LiveData<ArticleVaccinesEntity> =
        newsDao.getVaccineArticleByUrl(url)

    fun getAllTeams(): DataSource.Factory<Int, TeamsEntity> = newsDao.getAllTeams()

    fun insertTeams(teams: List<TeamsEntity>) = newsDao.insertTeams(teams)

    fun getAllTweet(): DataSource.Factory<Int, TweetEntity> = newsDao.getAllTweet()

    fun insertTweet(tweet: List<TweetEntity>) = newsDao.insertTweets(tweet)
}