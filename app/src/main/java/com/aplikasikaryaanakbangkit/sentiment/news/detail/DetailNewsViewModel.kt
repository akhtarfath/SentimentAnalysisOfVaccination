package com.aplikasikaryaanakbangkit.sentiment.news.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aplikasikaryaanakbangkit.sentiment.core.data.SentimentRepository
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Resource

class DetailNewsViewModel(private val _newsRepository: SentimentRepository) : ViewModel() {

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