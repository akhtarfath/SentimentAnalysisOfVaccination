package com.aplikasikaryaanakbangkit.sentiment.data.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.LocalDataSource
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.data.source.remote.ApiResponse
import com.aplikasikaryaanakbangkit.sentiment.data.source.remote.RemoteDataSource
import com.aplikasikaryaanakbangkit.sentiment.data.source.remote.response.ArticlesItemResponse
import com.aplikasikaryaanakbangkit.sentiment.utils.AppExecutors
import com.aplikasikaryaanakbangkit.sentiment.vo.Resource

class NewsRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : NewsDataSource {

    companion object {
        @Volatile
        private var instance: NewsRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance
                    ?: NewsRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getCovidHeadlines(): LiveData<Resource<PagedList<ArticleCovidEntity>>> {
        return object :
            NetworkBoundResource<PagedList<ArticleCovidEntity>, ArrayList<ArticlesItemResponse>>(
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

            public override fun createCall(): LiveData<ApiResponse<ArrayList<ArticlesItemResponse>>> =
                remoteDataSource.getresultCovidHeadlines()

            public override fun saveCallResult(data: ArrayList<ArticlesItemResponse>) {
                val articleList = ArrayList<ArticleCovidEntity>()
                for (response in data) {
                    val articles = ArticleCovidEntity(
                        response.publishedAt,
                        response.author,
                        response.urlToImage,
                        response.description,
                        response.title,
                        response.url,
                        response.content
                    )
                    articleList.add(articles)
                }
            }
        }.asLiveData()
    }

    override fun getVaccineNews(): LiveData<Resource<PagedList<ArticleVaccinesEntity>>> {
        return object :
            NetworkBoundResource<PagedList<ArticleVaccinesEntity>, ArrayList<ArticlesItemResponse>>(
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

            public override fun createCall(): LiveData<ApiResponse<ArrayList<ArticlesItemResponse>>> =
                remoteDataSource.getresultVaccineNews()

            public override fun saveCallResult(data: ArrayList<ArticlesItemResponse>) {
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
            }
        }.asLiveData()
    }

    override fun getCovidHeadlinesByUrl(url: String): LiveData<Resource<ArticleCovidEntity>> {
        return object :
            NetworkBoundResource<ArticleCovidEntity, ArrayList<ArticlesItemResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<ArticleCovidEntity> =
                localDataSource.getCovidArticleByUrl(url)

            override fun shouldFetch(data: ArticleCovidEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<ArrayList<ArticlesItemResponse>>> =
                remoteDataSource.getresultCovidHeadlines()

            override fun saveCallResult(data: ArrayList<ArticlesItemResponse>) {
                lateinit var article: ArticleCovidEntity
                for (response in data) {
                    if (response.url == url) {
                        article = ArticleCovidEntity(
                            response.publishedAt,
                            response.author,
                            response.urlToImage,
                            response.description,
                            response.title,
                            response.url,
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
            NetworkBoundResource<ArticleVaccinesEntity, ArrayList<ArticlesItemResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<ArticleVaccinesEntity> =
                localDataSource.getVaccineArticleByUrl(url)

            override fun shouldFetch(data: ArticleVaccinesEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<ArrayList<ArticlesItemResponse>>> =
                remoteDataSource.getresultVaccineNews()

            override fun saveCallResult(data: ArrayList<ArticlesItemResponse>) {
                lateinit var article: ArticleVaccinesEntity
                for (response in data) {
                    if (response.url == url) {
                        article = ArticleVaccinesEntity(
                            response.publishedAt,
                            response.author,
                            response.urlToImage,
                            response.description,
                            response.title,
                            response.url,
                            response.content
                        )
                    }
                }
                localDataSource.insertVaccineArticles(listOf(article))
            }

        }.asLiveData()
    }
}