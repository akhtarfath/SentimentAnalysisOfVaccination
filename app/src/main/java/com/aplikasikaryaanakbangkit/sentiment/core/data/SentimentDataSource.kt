package com.aplikasikaryaanakbangkit.sentiment.core.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TeamsEntity
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Resource

interface SentimentDataSource {
    fun getCovidHeadlines(): LiveData<Resource<PagedList<ArticleCovidEntity>>>
    fun getVaccineNews(): LiveData<Resource<PagedList<ArticleVaccinesEntity>>>
    fun getCovidHeadlinesByUrl(url: String): LiveData<Resource<ArticleCovidEntity>>
    fun getVaccineNewsByUrl(url: String): LiveData<Resource<ArticleVaccinesEntity>>

    fun getAllTeams(): LiveData<Resource<PagedList<TeamsEntity>>>
}