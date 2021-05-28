package com.aplikasikaryaanakbangkit.sentiment.core.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.LocalDataSource
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.covid.*
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.teams.TeamsEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.DataItemTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.PublicMetricsTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.TweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.UserItemsTweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.RemoteDataSource
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.network.ApiResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.covid.*
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.news.ArticlesItemResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.teams.TeamsResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet.DataItemTweetResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet.PublicMetricsTweetResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet.UserItemsTweetResponse
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

            override fun shouldFetch(data: PagedList<ArticleCovidEntity>?): Boolean =
                data == null || data.isEmpty()

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

            override fun shouldFetch(data: PagedList<ArticleVaccinesEntity>?): Boolean =
                data == null || data.isEmpty()

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

            override fun shouldFetch(data: ArticleCovidEntity?): Boolean = data == null

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

            override fun shouldFetch(data: ArticleVaccinesEntity?): Boolean = data == null

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
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TeamsResponse>>> =
                remoteDataSource.getAllTeams()

            override fun saveCallResult(data: List<TeamsResponse>) {
                val teamsList = ArrayList<TeamsEntity>()
                for (response in data) {
                    val teams = TeamsEntity(
                        response.id,
                        response.name,
                        response.urlPicture
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
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<DataItemTweetResponse>>> =
                remoteDataSource.getAllPost()

            override fun saveCallResult(data: List<DataItemTweetResponse>) {
                val tweetList = ArrayList<DataItemTweetEntity>()
                for (response in data) {
                    val tweet = DataItemTweetEntity(
                        response.authorId.toString(),
                        response.createdAt.toString(),
                        response.text.toString(),
                        response.authorId.toString()
                    )
                    tweetList.add(tweet)
                }
                localDataSource.insertTweet(tweetList)
            }
        }.asLiveData()
    }

    override fun getPublicMetrics(id: String): LiveData<Resource<PublicMetricsTweetEntity>> {
        return object :
            NetworkBoundResource<PublicMetricsTweetEntity, PublicMetricsTweetResponse>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PublicMetricsTweetEntity> =
                localDataSource.getTweetWithMetrics(id)

            override fun shouldFetch(data: PublicMetricsTweetEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<PublicMetricsTweetResponse>> =
                remoteDataSource.getPublicMetrics()

            override fun saveCallResult(data: PublicMetricsTweetResponse) {
                localDataSource.updatePostByMetrics(
                    data.likeCount!!, data.replyCount!!, data.quoteCount!!,
                    data.retweetCount!!, id
                )
            }
        }.asLiveData()
    }

    override fun getAllTweet(): LiveData<List<TweetEntity>> =
        localDataSource.getAllTweets()

    override fun getConfirmed(): LiveData<Resource<ConfirmedGlobalCovidEntity>> {
        return object :
                NetworkBoundResource<ConfirmedGlobalCovidEntity, ConfirmedGlobalCovidResponse>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<ConfirmedGlobalCovidEntity> =
                    localDataSource.getConfirmedGlobalCovid()

            override fun shouldFetch(data: ConfirmedGlobalCovidEntity?): Boolean =
                    data == null

            override fun createCall(): LiveData<ApiResponse<ConfirmedGlobalCovidResponse>> =
                    remoteDataSource.getConfirmedGlobalCovid()

            override fun saveCallResult(data: ConfirmedGlobalCovidResponse) {
                val covidGlobal =
                        ConfirmedGlobalCovidEntity(
                                1,
                                data.detail,
                                data.value
                        )
                localDataSource.insertConfirmedGlobalCovid(covidGlobal)
            }
        }.asLiveData()
    }

    //covid
    override fun getDeath(): LiveData<Resource<DeathGlobalCovidEntity>> {
        return object :
                NetworkBoundResource<DeathGlobalCovidEntity, DeathGlobalCovidResponse>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<DeathGlobalCovidEntity> =
                    localDataSource.getDeathlobalCovid()

            override fun shouldFetch(data: DeathGlobalCovidEntity?): Boolean =
                    data == null

            override fun createCall(): LiveData<ApiResponse<DeathGlobalCovidResponse>> =
                    remoteDataSource.getDeathGlobalCovid()

            override fun saveCallResult(data: DeathGlobalCovidResponse) {
                val covidGlobal =
                        DeathGlobalCovidEntity(
                                1,
                                data.detail,
                                data.value
                        )
                localDataSource.insertDeathGlobalCovid(covidGlobal)
            }
        }.asLiveData()
    }

    override fun getRecovered(): LiveData<Resource<RecoveredGlobalCovidEntity>> {
        return object :
                NetworkBoundResource<RecoveredGlobalCovidEntity, RecoveredGlobalCovidResponse>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<RecoveredGlobalCovidEntity> =
                    localDataSource.getRecoveredGlobalCovid()

            override fun shouldFetch(data: RecoveredGlobalCovidEntity?): Boolean =
                    data == null

            override fun createCall(): LiveData<ApiResponse<RecoveredGlobalCovidResponse>> =
                    remoteDataSource.getRecoveredGlobalCovid()

            override fun saveCallResult(data: RecoveredGlobalCovidResponse) {
                val covidGlobal =
                        RecoveredGlobalCovidEntity(
                                1,
                                data.detail,
                                data.value
                        )
                localDataSource.insertRecoveredGlobalCovid(covidGlobal)
            }
        }.asLiveData()
    }

    override fun getAllGlobalCovid(): LiveData<GlobalCovidEntity> =
            localDataSource.getAllGlobalCovid()

    override fun getAllIDCovid(): LiveData<Resource<IDCovidItemEntity>> {
        return object :
                NetworkBoundResource<IDCovidItemEntity, IDCovidItemResponse>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<IDCovidItemEntity> =
                    localDataSource.getAllIDCovid()

            override fun shouldFetch(data: IDCovidItemEntity?): Boolean =
                    data == null

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
}