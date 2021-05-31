package com.aplikasikaryaanakbangkit.sentiment.core.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aplikasikaryaanakbangkit.sentiment.core.data.SAVRepository
import com.aplikasikaryaanakbangkit.sentiment.core.di.Injection
import com.aplikasikaryaanakbangkit.sentiment.home.HomeViewModel
import com.aplikasikaryaanakbangkit.sentiment.news.NewsViewModel
import com.aplikasikaryaanakbangkit.sentiment.news.detail.DetailNewsViewModel
import com.aplikasikaryaanakbangkit.sentiment.sentiment.SentimentAnalysisViewModel
import com.aplikasikaryaanakbangkit.sentiment.team.TeamViewModel
import com.aplikasikaryaanakbangkit.sentiment.vaccination.VaccinationViewModel

class ViewModelFactory private constructor(
    private val _repository: SAVRepository
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
                HomeViewModel(_repository) as T
            }
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> {
                NewsViewModel(_repository) as T
            }
            modelClass.isAssignableFrom(DetailNewsViewModel::class.java) -> {
                DetailNewsViewModel(_repository) as T
            }
            modelClass.isAssignableFrom(TeamViewModel::class.java) -> {
                TeamViewModel(_repository) as T
            }
            modelClass.isAssignableFrom(SentimentAnalysisViewModel::class.java) -> {
                SentimentAnalysisViewModel(_repository) as T
            }
            modelClass.isAssignableFrom(VaccinationViewModel::class.java) -> {
                VaccinationViewModel(_repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}