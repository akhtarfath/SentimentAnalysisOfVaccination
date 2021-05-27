package com.aplikasikaryaanakbangkit.sentiment.core.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.*
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Resource

interface SAVDataSource {
    // covid
    fun getCovidHeadlines(): LiveData<Resource<PagedList<ArticleCovidEntity>>>
    fun getVaccineNews(): LiveData<Resource<PagedList<ArticleVaccinesEntity>>>
    fun getCovidHeadlinesByUrl(url: String): LiveData<Resource<ArticleCovidEntity>>
    fun getVaccineNewsByUrl(url: String): LiveData<Resource<ArticleVaccinesEntity>>

    // team
    fun getAllTeams(): LiveData<Resource<PagedList<TeamsEntity>>>

    // tweet
    fun getAllProfile(): LiveData<Resource<List<UserItemsTweetEntity>>>
    fun getAllPost(): LiveData<Resource<List<DataItemTweetEntity>>>
    fun getPublicMetrics(id: String): LiveData<Resource<DataItemTweetEntity>>
    fun getAllTweet(): LiveData<List<TweetEntity>>
}