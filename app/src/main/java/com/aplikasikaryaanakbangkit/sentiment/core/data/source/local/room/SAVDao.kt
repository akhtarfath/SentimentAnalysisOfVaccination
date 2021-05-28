package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid.*
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.teams.TeamsEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.DataItemTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.PublicMetricsTweetEntity
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
    @Query("SELECT * FROM tweetPost")
    fun getAllTweet(): LiveData<List<DataItemTweetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweets(tweet: List<DataItemTweetEntity>)

    @Query("SELECT * FROM tweetPost WHERE id = :id")
    fun getTweetById(id: String): LiveData<DataItemTweetEntity>

    @Query("SELECT * FROM tweetPost WHERE id = :id")
    fun getTweetMetrics(id: String): LiveData<PublicMetricsTweetEntity>

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

    //covid
    @Query("SELECT * FROM confirmGlobalCovid")
    fun getConfirmedGlobalCovid(): LiveData<ConfirmedGlobalCovidEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConfirmedGlobalCovid(confirmedCovid: ConfirmedGlobalCovidEntity)

    @Query("SELECT * FROM deathGlobalCovid")
    fun getDeathlobalCovid(): LiveData<DeathGlobalCovidEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeathGlobalCovid(deathCovid: DeathGlobalCovidEntity)

    @Query("SELECT * FROM recoveredGlobalEntity")
    fun getRecoveredGlobalCovid(): LiveData<RecoveredGlobalCovidEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecoveredGlobalCovid(recoveredCovid: RecoveredGlobalCovidEntity)

    @Query("SELECT death.valueDeath as deathGlobal, confirm.valueConfirmed as confirmedGlobal, recovered.valueRecovered as recoveredGlobal FROM deathGlobalCovid as death, confirmGlobalCovid as confirm, recoveredGlobalEntity as recovered")
    fun getAllGlobalCovid(): LiveData<GlobalCovidEntity>

    @Query("SELECT * FROM idCovid")
    fun getAllIDCovid(): LiveData<IDCovidItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIDCOvid(idCovid: IDCovidItemEntity)

    //vaccination
    @Query("SELECT * FROM vaccineCakupan")
    fun getVaccineCakupan(): LiveData<VaccinationCakupanEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaccineCakupan(vaccination: VaccinationCakupanEntity)

    @Query("SELECT * FROM vaccineLansia")
    fun getVaccineLansia(): LiveData<VaccinationLansiaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaccineLansia(vaccination: VaccinationLansiaEntity)

    @Query("SELECT * FROM vaccineMonitoring")
    fun getVaccineMonitoring(): LiveData<VaccinationMonitoringItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaccineMonitoring(vaccination: VaccinationMonitoringItemEntity)

    @Query("SELECT * FROM vaccinePetugas")
    fun getVaccinePetugas(): LiveData<VaccinationPetugasPublikEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaccinePetugas(vaccination: VaccinationPetugasPublikEntity)

    @Query("SELECT * FROM vaccineSDM")
    fun getVaccineSDM(): LiveData<VaccinationSdmKesehatanEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaccineSDM(vaccination: VaccinationSdmKesehatanEntity)
}