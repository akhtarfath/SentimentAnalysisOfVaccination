package com.aplikasikaryaanakbangkit.sentiment.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aplikasikaryaanakbangkit.sentiment.data.source.NewsRepository
import com.aplikasikaryaanakbangkit.sentiment.di.Injection
import com.aplikasikaryaanakbangkit.sentiment.ui.detail.DetailNewsViewModel
import com.aplikasikaryaanakbangkit.sentiment.ui.news.NewsViewModel

class ViewModelFactory private constructor(private val mNewsRepository: NewsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> {
                NewsViewModel(mNewsRepository) as T
            }
            modelClass.isAssignableFrom(DetailNewsViewModel::class.java) -> {
                DetailNewsViewModel(mNewsRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}