package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
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

    //news
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

    //teams
    fun getAllTeams(): DataSource.Factory<Int, TeamsEntity> = SAVDao.getAllTeams()

    fun insertTeams(teams: List<TeamsEntity>) = SAVDao.insertTeams(teams)

    //tweets
    fun getAllPost(): LiveData<List<DataItemTweetEntity>> = SAVDao.getAllTweet()

    fun insertTweet(tweet: List<DataItemTweetEntity>) = SAVDao.insertTweets(tweet)

    fun getAllTweetProfile(): LiveData<List<UserItemsTweetEntity>> =
            SAVDao.getAllTweetProfile()

    fun insertTweetProfile(profile: List<UserItemsTweetEntity>) =
            SAVDao.insertTweetProfile(profile)

    fun getAllTweets(): LiveData<List<TweetEntity>> = SAVDao.getAllTweets()

    //  fun updateTweets(result: String, textTweet: String): LiveData<TweetEntity> =
    //        SAVDao.updateTweets(result, textTweet)

    //covid
    fun getAllGlobalCovid(): LiveData<GlobalCovidEntity> =
            SAVDao.getAllGlobalCovid()

    fun insertAllGlobalCovid(globalCovid: GlobalCovidEntity) =
            SAVDao.insertAllGlobalCovid(globalCovid)

    fun getAllIDCovid(): LiveData<IDCovidItemEntity> =
            SAVDao.getAllIDCovid()

    fun insertIDCovid(idCovid: IDCovidItemEntity) =
            SAVDao.insertIDCovid(idCovid)

    //vaksin
    fun getVaccineCoverage(): LiveData<VaccinationCoverageEntity> =
            SAVDao.getVaccineCoverage()

    fun insertVaccineCoverage(vaccination: VaccinationCoverageEntity) =
            SAVDao.insertVaccineCoverage(vaccination)

    fun getVaccineElderly(): LiveData<VaccinationElderlyEntity> =
            SAVDao.getVaccineElderly()

    fun insertVaccineElderly(vaccination: VaccinationElderlyEntity) =
            SAVDao.insertVaccineElderly(vaccination)

    fun getVaccineMonitoring(): LiveData<VaccinationMonitoringItemEntity> =
            SAVDao.getVaccineMonitoring()

    fun insertVaccineMonitoring(vaccination: VaccinationMonitoringItemEntity) =
            SAVDao.insertVaccineMonitoring(vaccination)

    fun getVaccinePublicOfficer(): LiveData<VaccinationPublicOfficerEntity> =
            SAVDao.getVaccinePublicOfficer()

    fun insertVaccinePublicOfficer(vaccination: VaccinationPublicOfficerEntity) =
            SAVDao.insertVaccinePublicOfficer(vaccination)

    fun getVaccineSDM(): LiveData<VaccinationHealthHREntity> =
            SAVDao.getVaccineHealthHR()

    fun insertVaccineSDM(vaccination: VaccinationHealthHREntity) =
            SAVDao.insertVaccineHealthHR(vaccination)

    //sentiment
    fun getSentimentAnalysis(): LiveData<SentimentEntity> =
            SAVDao.getSentimentAnalysis()

    fun insertSentimentAnalysis(sentiment: SentimentEntity) =
            SAVDao.insertSentimentAnalysis(sentiment)
}