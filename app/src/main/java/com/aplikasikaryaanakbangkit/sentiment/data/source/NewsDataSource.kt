package com.aplikasikaryaanakbangkit.sentiment.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.vo.Resource

interface NewsDataSource {

    fun getCovidHeadlines(): LiveData<Resource<PagedList<ArticleCovidEntity>>>
    fun getVaccineNews(): LiveData<Resource<PagedList<ArticleVaccinesEntity>>>
    fun getCovidHeadlinesByUrl(url: String): LiveData<Resource<ArticleCovidEntity>>
    fun getVaccineNewsByUrl(url: String): LiveData<Resource<ArticleVaccinesEntity>>

}