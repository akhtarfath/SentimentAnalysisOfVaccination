package com.aplikasikaryaanakbangkit.sentiment.core.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.LocalDataSource
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.*
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.RemoteDataSource
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.network.ApiResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.*
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

    override fun getAllPostByProfile(authorId: String): LiveData<Resource<List<DataItemTweetEntity>>> {
        return object :
            NetworkBoundResource<List<DataItemTweetEntity>, List<DataItemTweetResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<List<DataItemTweetEntity>> =
                localDataSource.getTweetWithProfile(authorId)

            override fun shouldFetch(data: List<DataItemTweetEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<DataItemTweetResponse>>> =
                remoteDataSource.getProfileWithPost()

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

    override fun getPublicMetrics(id: String): LiveData<Resource<DataItemTweetEntity>> {
        return object :
            NetworkBoundResource<DataItemTweetEntity, PublicMetricsTweetResponse>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<DataItemTweetEntity> =
                localDataSource.getTweetWithMetrics(id)

            override fun shouldFetch(data: DataItemTweetEntity?): Boolean =
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
}