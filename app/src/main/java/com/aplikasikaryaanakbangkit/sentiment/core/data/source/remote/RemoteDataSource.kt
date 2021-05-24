package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api.NewsService
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.network.ApiResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.ArticlesItemResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor() {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getResultCovidHeadlines(): LiveData<ApiResponse<List<ArticlesItemResponse>>> {
        val resultCovidHeadlines = MutableLiveData<ApiResponse<List<ArticlesItemResponse>>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            NewsService.create()
                .getCovidHeadlines()
                .enqueue(object : Callback<NewsResponse> {
                    override fun onResponse(
                        call: Call<NewsResponse>,
                        response: Response<NewsResponse>
                    ) {
                        resultCovidHeadlines.postValue(
                            response.body()?.let { ApiResponse.success(it.articles) })
                    }

                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        ApiResponse.error(t.message.toString(), mutableListOf(resultCovidHeadlines))
                        Log.e("RemoteDataSource", t.message.toString())
                    }

                })
        }, 1500)

        return resultCovidHeadlines
    }

    fun getResultVaccineNews(): LiveData<ApiResponse<List<ArticlesItemResponse>>> {
        val resultVaccineNews = MutableLiveData<ApiResponse<List<ArticlesItemResponse>>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            NewsService.create()
                .getVaccineNews()
                .enqueue(object : Callback<NewsResponse> {
                    override fun onResponse(
                        call: Call<NewsResponse>,
                        response: Response<NewsResponse>
                    ) {
                        resultVaccineNews.postValue(
                            response.body()?.let { ApiResponse.success(it.articles) })
                    }

                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        ApiResponse.error(t.message.toString(), mutableListOf(resultVaccineNews))
                    }

                })
        }, 1500)

        return resultVaccineNews
    }
}

