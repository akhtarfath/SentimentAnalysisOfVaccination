package com.aplikasikaryaanakbangkit.sentiment.core.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aplikasikaryaanakbangkit.sentiment.core.data.NewsRepository
import com.aplikasikaryaanakbangkit.sentiment.core.di.Injection
import com.aplikasikaryaanakbangkit.sentiment.home.HomeViewModel
import com.aplikasikaryaanakbangkit.sentiment.news.NewsViewModel
import com.aplikasikaryaanakbangkit.sentiment.news.detail.DetailNewsViewModel

class ViewModelFactory private constructor(
    private val _newsRepository: NewsRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.newsRepository(context)
                ).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel() as T
            }
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> {
                NewsViewModel(_newsRepository) as T
            }
            modelClass.isAssignableFrom(DetailNewsViewModel::class.java) -> {
                DetailNewsViewModel(_newsRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}