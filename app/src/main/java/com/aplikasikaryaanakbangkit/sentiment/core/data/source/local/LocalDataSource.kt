package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid.*
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.teams.TeamsEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.DataItemTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.PublicMetricsTweetEntity
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

    fun updatePostByMetrics(
        likeCount: Int,
        replyCount: Int,
        quoteCount: Int,
        retweetCount: Int,
        id: String
    ) =
        SAVDao.updatePostByMetrics(likeCount, replyCount, quoteCount, retweetCount, id)

    fun getTweetWithMetrics(id: String): LiveData<PublicMetricsTweetEntity> =
        SAVDao.getTweetMetrics(id)

    fun getAllTweets(): LiveData<List<TweetEntity>> = SAVDao.getAllTweets()

    //covid
    fun getConfirmedGlobalCovid(): LiveData<ConfirmedGlobalCovidEntity> =
        SAVDao.getConfirmedGlobalCovid()

    fun insertConfirmedGlobalCovid(confirmedCovid: ConfirmedGlobalCovidEntity) =
        SAVDao.insertConfirmedGlobalCovid(confirmedCovid)

    fun getDeathlobalCovid(): LiveData<DeathGlobalCovidEntity> =
        SAVDao.getDeathlobalCovid()

    fun insertDeathGlobalCovid(deathCovid: DeathGlobalCovidEntity) =
        SAVDao.insertDeathGlobalCovid(deathCovid)

    fun getRecoveredGlobalCovid(): LiveData<RecoveredGlobalCovidEntity> =
        SAVDao.getRecoveredGlobalCovid()

    fun insertRecoveredGlobalCovid(recoveredCovid: RecoveredGlobalCovidEntity) =
        SAVDao.insertRecoveredGlobalCovid(recoveredCovid)

    fun getAllGlobalCovid(): LiveData<GlobalCovidEntity> =
        SAVDao.getAllGlobalCovid()

    fun getAllIDCovid(): LiveData<IDCovidItemEntity> =
        SAVDao.getAllIDCovid()

    fun insertIDCovid(idCovid: IDCovidItemEntity) =
        SAVDao.insertIDCOvid(idCovid)

    //vaksin
    fun getVaccineCakupan(): LiveData<VaccinationCakupanEntity> =
        SAVDao.getVaccineCakupan()

    fun insertVaccineCakupan(vaccination: VaccinationCakupanEntity) =
        SAVDao.insertVaccineCakupan(vaccination)

    fun getVaccineLansia(): LiveData<VaccinationLansiaEntity> =
        SAVDao.getVaccineLansia()

    fun insertVaccineLansia(vaccination: VaccinationLansiaEntity) =
        SAVDao.insertVaccineLansia(vaccination)

    fun getVaccineMonitoring(): LiveData<VaccinationMonitoringItemEntity> =
        SAVDao.getVaccineMonitoring()

    fun insertVaccineMonitoring(vaccination: VaccinationMonitoringItemEntity) =
        SAVDao.insertVaccineMonitoring(vaccination)

    fun getVaccinePetugas(): LiveData<VaccinationPetugasPublikEntity> =
        SAVDao.getVaccinePetugas()

    fun insertVaccinePetugas(vaccination: VaccinationPetugasPublikEntity) =
        SAVDao.insertVaccinePetugas(vaccination)

    fun getVaccineSDM(): LiveData<VaccinationSdmKesehatanEntity> =
        SAVDao.getVaccineSDM()

    fun insertVaccineSDM(vaccination: VaccinationSdmKesehatanEntity) =
        SAVDao.insertVaccineSDM(vaccination)
}