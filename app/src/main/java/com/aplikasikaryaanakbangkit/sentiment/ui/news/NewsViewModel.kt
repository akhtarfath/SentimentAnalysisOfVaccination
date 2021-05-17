package com.aplikasikaryaanakbangkit.sentiment.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.aplikasikaryaanakbangkit.sentiment.data.source.NewsRepository
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.vo.Resource

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    fun getDataCovidHeadlines(): LiveData<Resource<PagedList<ArticleCovidEntity>>> =
        newsRepository.getCovidHeadlines()

    fun getDataVaccineNews(): LiveData<Resource<PagedList<ArticleVaccinesEntity>>> =
        newsRepository.getVaccineNews()
}