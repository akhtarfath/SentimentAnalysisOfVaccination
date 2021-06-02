package com.aplikasikaryaanakbangkit.sentiment.core.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.LocalDataSource
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid.GlobalCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid.IDCovidItemEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.sentiment.*
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.teams.TeamsEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.DataItemTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.TweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.UserItemsTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.vaccination.*
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.RemoteDataSource
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.network.ApiResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.covid.GlobalCovidResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.covid.IDCovidItemResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.news.ArticlesItemResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.sentiment.SentimentResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.sentiment.TextTweet
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.teams.TeamsResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet.DataItemTweetResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet.UserItemsTweetResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination.VaccinationMonitoringItemResponse
import com.aplikasikaryaanakbangkit.sentiment.core.utils.AppExecutors
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Resource

class SAVRepository private constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : SAVDataSource {

    companion object {
        @Volatile
        private var instance: SAVRepository? = null

        fun getInstance(
                remoteData: RemoteDataSource,
                localData: LocalDataSource,
                appExecutors: AppExecutors
        ): SAVRepository =
                instance ?: synchronized(this) {
                    instance
                            ?: SAVRepository(remoteData, localData, appExecutors).apply {
                                instance = this
                            }
                }
    }

    //news
    override fun getCovidHeadlines(): LiveData<Resource<PagedList<ArticleCovidEntity>>> {
        return object :
                NetworkBoundResource<PagedList<ArticleCovidEntity>, List<ArticlesItemResponse>>(
                        appExecutors
                ) {

            override fun loadFromDB(): LiveData<PagedList<ArticleCovidEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()

                val data: DataSource.Factory<Int, ArticleCovidEntity> =
                        localDataSource.getCovidArticles()

                return LivePagedListBuilder(data, config).build()
            }

            override fun shouldFetch(data: PagedList<ArticleCovidEntity>?): Boolean {
                val dataResponse = ArrayList<ArticlesItemResponse>()
                return if(data?.size != dataResponse.size){
                    true
                }else{
                    data.isEmpty()
                }
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItemResponse>>> =
                    remoteDataSource.getResultCovidHeadlines()

            public override fun saveCallResult(data: List<ArticlesItemResponse>) {
                val articleList = ArrayList<ArticleCovidEntity>()
                for (response in data) {
                    val articles = ArticleCovidEntity(
                            response.url,
                            response.author,
                            response.urlToImage,
                            response.description,
                            response.title,
                            response.publishedAt,
                            response.content
                    )
                    articleList.add(articles)
                }
                localDataSource.insertCovidArticles(articleList)
            }
        }.asLiveData()
    }

    override fun getVaccineNews(): LiveData<Resource<PagedList<ArticleVaccinesEntity>>> {
        return object :
                NetworkBoundResource<PagedList<ArticleVaccinesEntity>, List<ArticlesItemResponse>>(
                        appExecutors
                ) {

            override fun loadFromDB(): LiveData<PagedList<ArticleVaccinesEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                val data: DataSource.Factory<Int, ArticleVaccinesEntity> =
                        localDataSource.getVaccineArticles()
                return LivePagedListBuilder(data, config).build()
            }

            override fun shouldFetch(data: PagedList<ArticleVaccinesEntity>?): Boolean {
                val dataResponse = ArrayList<ArticlesItemResponse>()
                return if (data?.size != dataResponse.size) {
                    true
                } else {
                    data.isEmpty()
                }
            }

            public override fun createCall(): LiveData<ApiResponse<List<ArticlesItemResponse>>> =
                    remoteDataSource.getResultVaccineNews()

            public override fun saveCallResult(data: List<ArticlesItemResponse>) {
                val articleList = ArrayList<ArticleVaccinesEntity>()
                for (response in data) {
                    val articles = ArticleVaccinesEntity(
                            response.url,
                            response.author,
                            response.urlToImage,
                            response.description,
                            response.title,
                            response.publishedAt,
                            response.content
                    )
                    articleList.add(articles)
                }

                localDataSource.insertVaccineArticles(articleList)
            }
        }.asLiveData()
    }

    override fun getCovidHeadlinesByUrl(url: String): LiveData<Resource<ArticleCovidEntity>> {
        return object :
                NetworkBoundResource<ArticleCovidEntity, List<ArticlesItemResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<ArticleCovidEntity> =
                    localDataSource.getCovidArticleByUrl(url)

            override fun shouldFetch(data: ArticleCovidEntity?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<ArticlesItemResponse>>> =
                    remoteDataSource.getResultCovidHeadlines()

            override fun saveCallResult(data: List<ArticlesItemResponse>) {
                lateinit var article: ArticleCovidEntity
                for (response in data) {
                    if (response.url == url) {
                        article = ArticleCovidEntity(
                                response.url,
                                response.author,
                                response.urlToImage,
                                response.description,
                                response.title,
                                response.publishedAt,
                                response.content
                        )
                    }
                }
                localDataSource.insertCovidArticles(listOf(article))
            }
        }.asLiveData()
    }

    override fun getVaccineNewsByUrl(url: String): LiveData<Resource<ArticleVaccinesEntity>> {
        return object :
                NetworkBoundResource<ArticleVaccinesEntity, List<ArticlesItemResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<ArticleVaccinesEntity> =
                    localDataSource.getVaccineArticleByUrl(url)

            override fun shouldFetch(data: ArticleVaccinesEntity?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<ArticlesItemResponse>>> =
                    remoteDataSource.getResultVaccineNews()

            override fun saveCallResult(data: List<ArticlesItemResponse>) {
                lateinit var article: ArticleVaccinesEntity
                for (response in data) {
                    if (response.url == url) {
                        article = ArticleVaccinesEntity(
                                response.url,
                                response.author,
                                response.urlToImage,
                                response.description,
                                response.title,
                                response.publishedAt,
                                response.content
                        )
                    }
                }
                localDataSource.insertVaccineArticles(listOf(article))
            }
        }.asLiveData()
    }

    //teams
    override fun getAllTeams(): LiveData<Resource<PagedList<TeamsEntity>>> {
        return object :
                NetworkBoundResource<PagedList<TeamsEntity>, List<TeamsResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<PagedList<TeamsEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(6)
                        .setPageSize(6)
                        .build()

                val data: DataSource.Factory<Int, TeamsEntity> =
                        localDataSource.getAllTeams()

                return LivePagedListBuilder(data, config).build()
            }

            override fun shouldFetch(data: PagedList<TeamsEntity>?): Boolean =
                    true

            override fun createCall(): LiveData<ApiResponse<List<TeamsResponse>>> =
                    remoteDataSource.getAllTeams()

            override fun saveCallResult(data: List<TeamsResponse>) {
                val teamsList = ArrayList<TeamsEntity>()
                for (response in data) {
                    val teams = TeamsEntity(
                            response.id,
                            response.name,
                            response.urlPicture,
                            response.urlLinkedIn,
                    )
                    teamsList.add(teams)
                }
                localDataSource.insertTeams(teamsList)
            }
        }.asLiveData()
    }

    //tweets
    override fun getAllProfile(): LiveData<Resource<List<UserItemsTweetEntity>>> {
        return object :
                NetworkBoundResource<List<UserItemsTweetEntity>, List<UserItemsTweetResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<List<UserItemsTweetEntity>> =
                    localDataSource.getAllTweetProfile()

            override fun shouldFetch(data: List<UserItemsTweetEntity>?): Boolean =
                    true

            override fun createCall(): LiveData<ApiResponse<List<UserItemsTweetResponse>>> =
                    remoteDataSource.getAllProfile()

            override fun saveCallResult(data: List<UserItemsTweetResponse>) {
                val tweetList = ArrayList<UserItemsTweetEntity>()
                for (response in data) {
                    val tweet = UserItemsTweetEntity(
                            response.id.toString(),
                            response.name.toString(),
                            response.profileImageUrl.toString(),
                            response.username.toString()
                    )
                    tweetList.add(tweet)
                }
                localDataSource.insertTweetProfile(tweetList)
            }
        }.asLiveData()
    }

    override fun getAllPost(): LiveData<Resource<List<DataItemTweetEntity>>> {
        return object :
                NetworkBoundResource<List<DataItemTweetEntity>, List<DataItemTweetResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<List<DataItemTweetEntity>> =
                    localDataSource.getAllPost()

            override fun shouldFetch(data: List<DataItemTweetEntity>?): Boolean =
                    true

            override fun createCall(): LiveData<ApiResponse<List<DataItemTweetResponse>>> =
                    remoteDataSource.getAllPost()

            override fun saveCallResult(data: List<DataItemTweetResponse>) {
                val tweetList = ArrayList<DataItemTweetEntity>()
                for (response in data) {
                    if (response.referencedTweets?.get(0)?.type.toString() != "retweeted") {
                        val tweet = DataItemTweetEntity(
                                response.authorId.toString(),
                                response.createdAt.toString(),
                                response.text.toString(),
                                response.authorId.toString(),
                                response.publicMetrics?.likeCount,
                                response.publicMetrics?.replyCount,
                                response.publicMetrics?.quoteCount,
                                response.publicMetrics?.retweetCount
                        )
                        tweetList.add(tweet)
                    }
                }
                localDataSource.insertTweet(tweetList)
            }
        }.asLiveData()
    }

    override fun getAllTweet(): LiveData<List<TweetEntity>> =
            localDataSource.getAllTweets()

    //covid
    override fun getAllGlobalCovid(): LiveData<Resource<GlobalCovidEntity>> {
        return object :
                NetworkBoundResource<GlobalCovidEntity, GlobalCovidResponse>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<GlobalCovidEntity> =
                    localDataSource.getAllGlobalCovid()

            override fun shouldFetch(data: GlobalCovidEntity?): Boolean {
                val dataResponse = GlobalCovidResponse()
                return data?.confirmedGlobal?.equals(dataResponse.confirmed) != true
            }

            override fun createCall(): LiveData<ApiResponse<GlobalCovidResponse>> =
                    remoteDataSource.getAllGlobalCovid()

            override fun saveCallResult(data: GlobalCovidResponse) {
                val covidGlobal =
                        GlobalCovidEntity(
                                1,
                                data.deaths?.value,
                                data.confirmed?.value,
                                data.recovered?.value
                        )
                localDataSource.insertAllGlobalCovid(covidGlobal)
            }
        }.asLiveData()
    }

    override fun getAllIDCovid(): LiveData<Resource<IDCovidItemEntity>> {
        return object :
                NetworkBoundResource<IDCovidItemEntity, IDCovidItemResponse>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<IDCovidItemEntity> =
                    localDataSource.getAllIDCovid()

            override fun shouldFetch(data: IDCovidItemEntity?): Boolean {
                val dataResponse = IDCovidItemResponse()
                return data?.confirmed?.equals(dataResponse.confirmed) != true
            }

            override fun createCall(): LiveData<ApiResponse<IDCovidItemResponse>> =
                    remoteDataSource.getAllIDCovid()

            override fun saveCallResult(data: IDCovidItemResponse) {
                val covidId =
                        IDCovidItemEntity(
                                data.lastUpdate,
                                data.confirmed,
                                data.recovered,
                                data.deaths
                        )
                localDataSource.insertIDCovid(covidId)
            }
        }.asLiveData()
    }

    //vaksinasi
    override fun getVaccination(): LiveData<Resource<VaccinationMonitoringItemEntity>> {
        return object :
                NetworkBoundResource<VaccinationMonitoringItemEntity, List<VaccinationMonitoringItemResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<VaccinationMonitoringItemEntity> =
                    localDataSource.getVaccineMonitoring()

            override fun shouldFetch(data: VaccinationMonitoringItemEntity?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<VaccinationMonitoringItemResponse>>> =
                    remoteDataSource.getAllVaccination()

            override fun saveCallResult(data: List<VaccinationMonitoringItemResponse>) {
                val dataSize = data.size - 1
                val vaccine =
                        VaccinationMonitoringItemEntity(
                                1,
                                data[dataSize].date,
                                data[dataSize].vaccinationTargetPublicOfficer,
                                data[dataSize].vaccination2,
                                data[dataSize].vaccination1,
                                data[dataSize].targetVaccinationHealthHR,
                                data[dataSize].targetVaccinationElderly,
                                data[dataSize].totalVaccinationTarget
                        )
                localDataSource.insertVaccineMonitoring(vaccine)
            }
        }.asLiveData()
    }

    override fun getVaccinationStepHealthHR(): LiveData<Resource<VaccinationHealthHREntity>> {
        return object :
                NetworkBoundResource<VaccinationHealthHREntity, List<VaccinationMonitoringItemResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<VaccinationHealthHREntity> =
                    localDataSource.getVaccineSDM()

            override fun shouldFetch(data: VaccinationHealthHREntity?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<VaccinationMonitoringItemResponse>>> =
                    remoteDataSource.getAllVaccination()

            override fun saveCallResult(data: List<VaccinationMonitoringItemResponse>) {
                val dataSize = data.size - 1
                val vaccine =
                        VaccinationHealthHREntity(
                                1,
                                data[dataSize].vaccinationStep?.healthHR?.vaccinated1,
                                data[dataSize].vaccinationStep?.healthHR?.totalVaccination2,
                                data[dataSize].vaccinationStep?.healthHR?.totalVaccination1,
                                data[dataSize].vaccinationStep?.healthHR?.vaccinated2,
                                data[dataSize].vaccinationStep?.healthHR?.delayedVaccination2,
                                data[dataSize].vaccinationStep?.healthHR?.delayedVaccination1
                        )
                localDataSource.insertVaccineSDM(vaccine)
            }
        }.asLiveData()
    }

    override fun getVaccinationStepElderly(): LiveData<Resource<VaccinationElderlyEntity>> {
        return object :
                NetworkBoundResource<VaccinationElderlyEntity, List<VaccinationMonitoringItemResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<VaccinationElderlyEntity> =
                    localDataSource.getVaccineElderly()

            override fun shouldFetch(data: VaccinationElderlyEntity?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<VaccinationMonitoringItemResponse>>> =
                    remoteDataSource.getAllVaccination()

            override fun saveCallResult(data: List<VaccinationMonitoringItemResponse>) {
                val dataSize = data.size - 1
                val vaccine =
                        VaccinationElderlyEntity(
                                1,
                                data[dataSize].vaccinationStep?.elderly?.vaccinated1,
                                data[dataSize].vaccinationStep?.elderly?.totalVaccinated2,
                                data[dataSize].vaccinationStep?.elderly?.totalVaccinated1,
                                data[dataSize].vaccinationStep?.elderly?.vaccinated2,
                                data[dataSize].vaccinationStep?.elderly?.delayedVaccination2,
                                data[dataSize].vaccinationStep?.elderly?.delayedVaccination1
                        )
                localDataSource.insertVaccineElderly(vaccine)
            }
        }.asLiveData()
    }

    override fun getVaccinationStepPublicOfficer(): LiveData<Resource<VaccinationPublicOfficerEntity>> {
        return object :
                NetworkBoundResource<VaccinationPublicOfficerEntity, List<VaccinationMonitoringItemResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<VaccinationPublicOfficerEntity> =
                    localDataSource.getVaccinePublicOfficer()

            override fun shouldFetch(data: VaccinationPublicOfficerEntity?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<VaccinationMonitoringItemResponse>>> =
                    remoteDataSource.getAllVaccination()

            override fun saveCallResult(data: List<VaccinationMonitoringItemResponse>) {
                val dataSize = data.size - 1
                val vaccine =
                        VaccinationPublicOfficerEntity(
                                1,
                                data[dataSize].vaccinationStep?.publicOfficer?.vaccinated1,
                                data[dataSize].vaccinationStep?.publicOfficer?.totalVaccination2,
                                data[dataSize].vaccinationStep?.publicOfficer?.totalVaccination1,
                                data[dataSize].vaccinationStep?.publicOfficer?.vaccinated2,
                                data[dataSize].vaccinationStep?.publicOfficer?.delayedVaccination2,
                                data[dataSize].vaccinationStep?.publicOfficer?.delayedVaccination1
                        )
                localDataSource.insertVaccinePublicOfficer(vaccine)
            }
        }.asLiveData()
    }

    override fun getVaccinationCoverage(): LiveData<Resource<VaccinationCoverageEntity>> {
        return object :
                NetworkBoundResource<VaccinationCoverageEntity, List<VaccinationMonitoringItemResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<VaccinationCoverageEntity> =
                    localDataSource.getVaccineCoverage()

            override fun shouldFetch(data: VaccinationCoverageEntity?): Boolean =
                    true

            override fun createCall(): LiveData<ApiResponse<List<VaccinationMonitoringItemResponse>>> =
                    remoteDataSource.getAllVaccination()

            override fun saveCallResult(data: List<VaccinationMonitoringItemResponse>) {
                val dataSize = data.size - 1
                val vaccine =
                        VaccinationCoverageEntity(
                                1,
                                data[dataSize].coverage?.vaccinationHealthHR2,
                                data[dataSize].coverage?.vaccinationHealthHR1,
                                data[dataSize].coverage?.vaccinationElderly1,
                                data[dataSize].coverage?.vaccinationPublicOfficer2,
                                data[dataSize].coverage?.vaccinationPublicOfficer1,
                                data[dataSize].coverage?.vaccination2,
                                data[dataSize].coverage?.vaccination1,
                                data[dataSize].coverage?.vaccinationElderly2
                        )
                localDataSource.insertVaccineCoverage(vaccine)
            }
        }.asLiveData()
    }

    //sentiment
    override fun getAnalysis(tweet: TextTweet): LiveData<Resource<SentimentEntity>> {
        return object :
                NetworkBoundResource<SentimentEntity, SentimentResponse>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<SentimentEntity> =
                    localDataSource.getSentimentAnalysis()

            override fun shouldFetch(data: SentimentEntity?): Boolean =
                    true

            override fun createCall(): LiveData<ApiResponse<SentimentResponse>> =
                    remoteDataSource.getAnalysis(tweet)

            override fun saveCallResult(data: SentimentResponse) {
                val sentiment =
                        SentimentEntity(
                                tweet.text_twitter,
                                data.result
                        )
                localDataSource.insertSentimentAnalysis(sentiment)
            }
        }.asLiveData()
    }

    override fun getNeutralAnalysis(): LiveData<NeutralAnalysisResultEntity> =
            localDataSource.getNeutralAnalysis()

    override fun getPositiveAnalysis(): LiveData<ProAnalysisResultEntity> =
            localDataSource.getPositiveAnalysis()

    override fun getNegativeAnalysis(): LiveData<ContraAnalysisResultEntity> =
            localDataSource.getNegativeAnalysis()

    override fun getAllAnalysis(): LiveData<AllAnalysisResultEntity> =
            localDataSource.getAllAnalysis()
}