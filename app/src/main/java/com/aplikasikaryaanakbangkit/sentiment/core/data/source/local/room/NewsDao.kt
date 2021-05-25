package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TeamsEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TweetEntity

@Dao
interface NewsDao {

    @Query("SELECT * FROM articleCovid ORDER BY publishedAt DESC")
    fun getCovidArticles(): DataSource.Factory<Int, ArticleCovidEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCovidArticles(article: List<ArticleCovidEntity>)

    @Query("SELECT * FROM articleCovid WHERE url = :url")
    fun getCovidArticleByUrl(url: String): LiveData<ArticleCovidEntity>

    @Query("SELECT * FROM articleVaccine ORDER BY publishedAt DESC")
    fun getVaccineArticles(): DataSource.Factory<Int, ArticleVaccinesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaccineArticles(article: List<ArticleVaccinesEntity>)

    @Query("SELECT * FROM articleVaccine WHERE url = :url")
    fun getVaccineArticleByUrl(url: String): LiveData<ArticleVaccinesEntity>

    @Query("SELECT * FROM teams")
    fun getAllTeams(): DataSource.Factory<Int, TeamsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(teams: List<TeamsEntity>)

    @Query("SELECT * FROM tweet")
    fun getAllTweet(): DataSource.Factory<Int, TweetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweets(tweet: List<TweetEntity>)
}