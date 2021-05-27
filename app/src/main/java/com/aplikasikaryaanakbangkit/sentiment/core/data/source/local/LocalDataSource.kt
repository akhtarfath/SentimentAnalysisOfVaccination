package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.*
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room.SAVDao

class LocalDataSource private constructor(
    private val SAVDao: SAVDao
) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(
            SAVDao: SAVDao
        ): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(SAVDao)
            }
    }

    fun getCovidArticles(): DataSource.Factory<Int, ArticleCovidEntity> =
        SAVDao.getCovidArticles()

    fun insertCovidArticles(article: List<ArticleCovidEntity>) =
        SAVDao.insertCovidArticles(article)

    fun getCovidArticleByUrl(url: String): LiveData<ArticleCovidEntity> =
        SAVDao.getCovidArticleByUrl(url)

    fun getVaccineArticles(): DataSource.Factory<Int, ArticleVaccinesEntity> =
        SAVDao.getVaccineArticles()

    fun insertVaccineArticles(article: List<ArticleVaccinesEntity>) =
        SAVDao.insertVaccineArticles(article)

    fun getVaccineArticleByUrl(url: String): LiveData<ArticleVaccinesEntity> =
        SAVDao.getVaccineArticleByUrl(url)

    fun getAllTeams(): DataSource.Factory<Int, TeamsEntity> = SAVDao.getAllTeams()

    fun insertTeams(teams: List<TeamsEntity>) = SAVDao.insertTeams(teams)

    fun getAllPost(): LiveData<List<DataItemTweetEntity>> = SAVDao.getAllTweet()

    fun insertTweet(tweet: List<DataItemTweetEntity>) = SAVDao.insertTweets(tweet)

    fun getAllTweetProfile(): LiveData<List<UserItemsTweetEntity>> =
        SAVDao.getAllTweetProfile()

    fun insertTweetProfile(profile: List<UserItemsTweetEntity>) =
        SAVDao.insertTweetProfile(profile)

    fun updatePostByMetrics(
        likeCount: Int,
        replyCount: Int,
        quoteCount: Int,
        retweetCount: Int,
        id: String
    ) =
        SAVDao.updatePostByMetrics(likeCount, replyCount, quoteCount, retweetCount, id)

    fun getTweetWithMetrics(id: String): LiveData<DataItemTweetEntity> =
        SAVDao.getTweetById(id)

    fun getAllTweets(): LiveData<List<TweetEntity>> = SAVDao.getAllTweets()
}