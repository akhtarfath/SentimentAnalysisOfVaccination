package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TweetEntity
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api.NewsService
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api.TweetService
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.network.ApiResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.*
import com.aplikasikaryaanakbangkit.sentiment.core.utils.JsonHelper
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
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

    fun getAllTeams(): LiveData<ApiResponse<List<TeamsResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<TeamsResponse>>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            try {
                val dataArray = jsonHelper.loadDataTeams()
                if (dataArray.isNotEmpty()) {
                    resultData.postValue(ApiResponse.success(dataArray))
                } else {
                    ApiResponse.empty("empty", mutableListOf(resultData))
                }
            } catch (e: JSONException) {
                ApiResponse.error(e.toString(), mutableListOf(resultData))
            }
        }, 2000)

        return resultData
    }

    fun getAllProfile(): LiveData<ApiResponse<List<UserItemsTweetResponse>>>{
        val resultTweet = MutableLiveData<ApiResponse<List<UserItemsTweetResponse>>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            TweetService.create()
                    .getAllTweet()
                    .enqueue(object : Callback<TweetResponse> {
                        override fun onResponse(
                                call: Call<TweetResponse>,
                                response: Response<TweetResponse>
                        ) {
                            ApiResponse.success(response.body()?.includes?.users).let {
                                resultTweet.postValue(
                                        it as ApiResponse<List<UserItemsTweetResponse>>)
                            }
                        }

                        override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
                            ApiResponse.error(t.message.toString(), mutableListOf(resultTweet))
                            Log.e("RemoteDataSource", t.message.toString())
                        }

                    })
        }, 1500)

        return resultTweet
    }

    fun getProfileWithPost(): LiveData<ApiResponse<List<DataItemTweetResponse>>>{
        val resultTweet = MutableLiveData<ApiResponse<List<DataItemTweetResponse>>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            TweetService.create()
                    .getAllTweet()
                    .enqueue(object : Callback<TweetResponse> {
                        override fun onResponse(
                                call: Call<TweetResponse>,
                                response: Response<TweetResponse>
                        ) {
                            resultTweet.postValue(
                                    response.body()?.let { ApiResponse.success(it.data as List<DataItemTweetResponse>) })
                        }

                        override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
                            ApiResponse.error(t.message.toString(), mutableListOf(resultTweet))
                            Log.e("RemoteDataSource", t.message.toString())
                        }

                    })
        }, 1500)

        return resultTweet
    }

    fun getPublicMetrics(): LiveData<ApiResponse<PublicMetricsTweetResponse>>{
        val resultTweet = MutableLiveData<ApiResponse<PublicMetricsTweetResponse>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            TweetService.create()
                    .getAllTweet()
                    .enqueue(object : Callback<TweetResponse> {
                        override fun onResponse(
                                call: Call<TweetResponse>,
                                response: Response<TweetResponse>
                        ) {
                            ApiResponse.success(response.body()?.data?.get(0)?.publicMetrics).let {
                                resultTweet.postValue(
                                        it as ApiResponse<PublicMetricsTweetResponse>)
                            }
                        }

                        override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
                            ApiResponse.error(t.message.toString(), mutableListOf(resultTweet))
                            Log.e("RemoteDataSource", t.message.toString())
                        }

                    })
        }, 1500)

        return resultTweet
    }
}

