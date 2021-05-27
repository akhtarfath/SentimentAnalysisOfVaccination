package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.*

@Dao
interface SentimentDao {

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

    @Query("SELECT * FROM tweetPost")
    fun getAllTweet(): LiveData<List<DataItemTweetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweets(tweet: List<DataItemTweetEntity>)

    @Query("SELECT * FROM tweetPost WHERE id = :id")
    fun getTweetById(id: String): LiveData<DataItemTweetEntity>

    @Query("UPDATE tweetPost SET likeCount = :likeCount, replyCount = :replyCount, quoteCount = :quoteCount, retweetCount = :retweetCount WHERE id = :id")
    fun updatePostByMetrics(
        likeCount: Int,
        replyCount: Int,
        quoteCount: Int,
        retweetCount: Int,
        id: String
    )

    @Query("SELECT * FROM tweetProfile")
    fun getAllTweetProfile(): LiveData<List<UserItemsTweetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweetProfile(profile: List<UserItemsTweetEntity>)

    @Query("SELECT tweetProfile.authorId as authorId, tweetProfile.name as name, tweetProfile.profile_image_url as imageUrl, tweetProfile.username as username, tweetPost.text as text, tweetPost.created_at as date, tweetPost.likeCount as likeCount, tweetPost.quoteCount as quoteCount, tweetPost.replyCount as replyCount, tweetPost.retweetCount as retweetCount FROM tweetProfile, tweetPost WHERE tweetProfile.authorId = tweetPost.authorId")
    fun getAllTweets(): LiveData<List<TweetEntity>>
}