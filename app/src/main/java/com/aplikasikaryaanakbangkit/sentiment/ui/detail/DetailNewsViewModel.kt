package com.aplikasikaryaanakbangkit.sentiment.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.data.source.NewsRepository
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.vo.Resource

class DetailNewsViewModel(private val _newsRepository: NewsRepository) : ViewModel() {

    val url = MutableLiveData<String>()

    fun setSelectedDetailNews(url: String) {
        this.url.value = url
    }

    var getDataDetailCovidHeadlines: LiveData<Resource<ArticleCovidEntity>> =
        Transformations.switchMap(url) { mUrl ->
            _newsRepository.getCovidHeadlinesByUrl(mUrl)
        }


    var getDataDetailVaccineNews: LiveData<Resource<ArticleVaccinesEntity>> =
        Transformations.switchMap(url) { mUrl ->
            _newsRepository.getVaccineNewsByUrl(mUrl)
        }
}