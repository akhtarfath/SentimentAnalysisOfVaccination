package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.*
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room.SentimentDao

class LocalDataSource private constructor(
    private val sentimentDao: SentimentDao
) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(
            sentimentDao: SentimentDao
        ): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(sentimentDao)
            }
    }

    fun getCovidArticles(): DataSource.Factory<Int, ArticleCovidEntity> =
        sentimentDao.getCovidArticles()

    fun insertCovidArticles(article: List<ArticleCovidEntity>) =
        sentimentDao.insertCovidArticles(article)

    fun getCovidArticleByUrl(url: String): LiveData<ArticleCovidEntity> =
        sentimentDao.getCovidArticleByUrl(url)

    fun getVaccineArticles(): DataSource.Factory<Int, ArticleVaccinesEntity> =
        sentimentDao.getVaccineArticles()

    fun insertVaccineArticles(article: List<ArticleVaccinesEntity>) =
        sentimentDao.insertVaccineArticles(article)

    fun getVaccineArticleByUrl(url: String): LiveData<ArticleVaccinesEntity> =
        sentimentDao.getVaccineArticleByUrl(url)

    fun getAllTeams(): DataSource.Factory<Int, TeamsEntity> = sentimentDao.getAllTeams()

    fun insertTeams(teams: List<TeamsEntity>) = sentimentDao.insertTeams(teams)

    fun getAllPost(): LiveData<List<DataItemTweetEntity>> = sentimentDao.getAllTweet()

    fun insertTweet(tweet: List<DataItemTweetEntity>) = sentimentDao.insertTweets(tweet)

    fun getAllTweetProfile(): LiveData<List<UserItemsTweetEntity>> =
        sentimentDao.getAllTweetProfile()

    fun insertTweetProfile(profile: List<UserItemsTweetEntity>) =
        sentimentDao.insertTweetProfile(profile)

    fun updatePostByMetrics(
        likeCount: Int,
        replyCount: Int,
        quoteCount: Int,
        retweetCount: Int,
        id: String
    ) =
        sentimentDao.updatePostByMetrics(likeCount, replyCount, quoteCount, retweetCount, id)

    fun getTweetWithMetrics(id: String): LiveData<DataItemTweetEntity> =
        sentimentDao.getTweetById(id)

    fun getAllTweets(): LiveData<List<TweetEntity>> = sentimentDao.getAllTweets()
}