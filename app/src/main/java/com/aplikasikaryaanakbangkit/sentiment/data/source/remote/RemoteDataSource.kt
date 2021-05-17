package com.aplikasikaryaanakbangkit.sentiment.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aplikasikaryaanakbangkit.sentiment.data.source.remote.api.NewsService
import com.aplikasikaryaanakbangkit.sentiment.data.source.remote.response.ArticlesItemResponse
import com.aplikasikaryaanakbangkit.sentiment.data.source.remote.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val newsService: NewsService) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: NewsService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getresultCovidHeadlines(): LiveData<ApiResponse<ArrayList<ArticlesItemResponse>>> {
        val resultCovidHeadlines = MutableLiveData<ApiResponse<ArrayList<ArticlesItemResponse>>>()
        NewsService.create()
            .getCovidHeadlines()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    handler.postDelayed({
                        resultCovidHeadlines.postValue(
                            response.body()?.let { ApiResponse.success(it.articles) })
                    }, SERVICE_LATENCY_IN_MILLIS)
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    ApiResponse.error(t.message.toString(), mutableListOf(resultCovidHeadlines))
                    Log.e("RemoteDataSource", t.message.toString())
                }

            })
        return resultCovidHeadlines
    }

    fun getresultVaccineNews(): LiveData<ApiResponse<ArrayList<ArticlesItemResponse>>> {
        val resultVaccineNews = MutableLiveData<ApiResponse<ArrayList<ArticlesItemResponse>>>()
        NewsService.create()
            .getVaccineNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    handler.postDelayed({
                        resultVaccineNews.postValue(
                            response.body()?.let { ApiResponse.success(it.articles) })
                    }, SERVICE_LATENCY_IN_MILLIS)
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    ApiResponse.error(t.message.toString(), mutableListOf(resultVaccineNews))
                }

            })
        return resultVaccineNews
    }
}