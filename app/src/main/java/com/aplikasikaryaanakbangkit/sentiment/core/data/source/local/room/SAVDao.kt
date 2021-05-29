package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid.GlobalCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid.IDCovidItemEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.sentiment.SentimentEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.teams.TeamsEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.DataItemTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.TweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.UserItemsTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination.*

@Dao
interface SAVDao {

    //news
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

    //teams
    @Query("SELECT * FROM teams")
    fun getAllTeams(): DataSource.Factory<Int, TeamsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(teams: List<TeamsEntity>)

    //tweet
    @Query("SELECT * FROM tweetPost WHERE text NOT LIKE 'RT @%'")
    fun getAllTweet(): LiveData<List<DataItemTweetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweets(tweet: List<DataItemTweetEntity>)

    @Query("SELECT * FROM tweetProfile")
    fun getAllTweetProfile(): LiveData<List<UserItemsTweetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweetProfile(profile: List<UserItemsTweetEntity>)

    @Query("SELECT tweetProfile.authorId as authorId, tweetProfile.name as name, tweetProfile.profile_image_url as imageUrl, tweetProfile.username as username, tweetPost.text as text, tweetPost.created_at as date, tweetPost.likeCount as likeCount, tweetPost.quoteCount as quoteCount, tweetPost.replyCount as replyCount, tweetPost.retweetCount as retweetCount FROM tweetProfile, tweetPost WHERE tweetProfile.authorId = tweetPost.authorId AND text NOT LIKE 'RT @%' ORDER BY date DESC")
    fun getAllTweets(): LiveData<List<TweetEntity>>

    //covid
    @Query("SELECT * FROM globalCovid")
    fun getAllGlobalCovid(): LiveData<GlobalCovidEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGlobalCovid(globalCovid: GlobalCovidEntity)

    @Query("SELECT * FROM idCovid")
    fun getAllIDCovid(): LiveData<IDCovidItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIDCovid(idCovid: IDCovidItemEntity)

    //vaccination
    @Query("SELECT * FROM vaccineCoverage")
    fun getVaccineCoverage(): LiveData<VaccinationCoverageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaccineCoverage(vaccination: VaccinationCoverageEntity)

    @Query("SELECT * FROM vaccineElderly")
    fun getVaccineElderly(): LiveData<VaccinationElderlyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaccineElderly(vaccination: VaccinationElderlyEntity)

    @Query("SELECT * FROM vaccineMonitoring")
    fun getVaccineMonitoring(): LiveData<VaccinationMonitoringItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaccineMonitoring(vaccination: VaccinationMonitoringItemEntity)

    @Query("SELECT * FROM vaccinePublicOfficer")
    fun getVaccinePublicOfficer(): LiveData<VaccinationPublicOfficerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaccinePublicOfficer(vaccination: VaccinationPublicOfficerEntity)

    @Query("SELECT * FROM vaccineSDM")
    fun getVaccineHealthHR(): LiveData<VaccinationHealthHREntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaccineHealthHR(vaccination: VaccinationHealthHREntity)

    //sentiment analysis
    @Query("SELECT * FROM sentimentAnalysis")
    fun getSentimentAnalysis(): LiveData<List<SentimentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSentimentAnalysis(sentiment: SentimentEntity)
}