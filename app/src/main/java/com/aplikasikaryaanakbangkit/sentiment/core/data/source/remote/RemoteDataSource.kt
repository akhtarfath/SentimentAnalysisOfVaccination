package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api.CovidService
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api.NewsService
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api.VaccinationService
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.network.ApiResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.network.TweetUtils
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.covid.*
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.news.ArticlesItemResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.news.NewsResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.teams.TeamsResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet.DataItemTweetResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet.TweetResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet.UserItemsTweetResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination.VaccinationMonitoringItemResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.vaccination.VaccinationResponse
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

    //news
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

    //teams
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

    //tweets
    fun getAllProfile(): LiveData<ApiResponse<List<UserItemsTweetResponse>>> {
        val resultTweet = MutableLiveData<ApiResponse<List<UserItemsTweetResponse>>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            TweetUtils.getApiService()
                .getAllTweet()
                .enqueue(object : Callback<TweetResponse> {
                    override fun onResponse(
                        call: Call<TweetResponse>,
                        response: Response<TweetResponse>
                    ) {
                        ApiResponse.success(response.body()?.includes?.users).let {
                            resultTweet.postValue(
                                it as ApiResponse<List<UserItemsTweetResponse>>
                            )
                        }
                    }

                    override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
                        ApiResponse.error(t.message.toString(), mutableListOf(resultTweet))
                        Log.d("RemoteDataSource", t.message.toString())
                    }

                })
        }, 1500)

        return resultTweet
    }

    fun getAllPost(): LiveData<ApiResponse<List<DataItemTweetResponse>>> {
        val resultTweet = MutableLiveData<ApiResponse<List<DataItemTweetResponse>>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            TweetUtils.getApiService()
                .getAllTweet()
                .enqueue(object : Callback<TweetResponse> {
                    override fun onResponse(
                        call: Call<TweetResponse>,
                        response: Response<TweetResponse>
                    ) {
                        resultTweet.postValue(
                            response.body()
                                ?.let { ApiResponse.success(it.data as List<DataItemTweetResponse>) })
                    }

                    override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
                        ApiResponse.error(t.message.toString(), mutableListOf(resultTweet))
                        Log.e("RemoteDataSource", t.message.toString())
                    }

                })
        }, 1500)

        return resultTweet
    }

    //covid
    fun getDeathGlobalCovid(): LiveData<ApiResponse<DeathGlobalCovidResponse>> {
        val resultData = MutableLiveData<ApiResponse<DeathGlobalCovidResponse>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            CovidService
                .create()
                .getGlobalCovid()
                .enqueue(object : Callback<GlobalCovidResponse> {
                    override fun onResponse(
                        call: Call<GlobalCovidResponse>,
                        response: Response<GlobalCovidResponse>
                    ) {

                        resultData.postValue(
                            response.body()
                                ?.let { ApiResponse.success(it.deaths as DeathGlobalCovidResponse) }
                        )
                    }

                    override fun onFailure(call: Call<GlobalCovidResponse>, t: Throwable) {
                        ApiResponse.error(t.message.toString(), mutableListOf(resultData))
                        Log.e("RemoteDataSource", t.message.toString())
                    }

                })
        }, 1500)

        return resultData
    }

    fun getRecoveredGlobalCovid(): LiveData<ApiResponse<RecoveredGlobalCovidResponse>> {
        val resultData = MutableLiveData<ApiResponse<RecoveredGlobalCovidResponse>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            CovidService
                .create()
                .getGlobalCovid()
                .enqueue(object : Callback<GlobalCovidResponse> {
                    override fun onResponse(
                        call: Call<GlobalCovidResponse>,
                        response: Response<GlobalCovidResponse>
                    ) {
                        resultData.postValue(
                            response.body()
                                ?.let { ApiResponse.success(it.recovered as RecoveredGlobalCovidResponse) }
                        )
                    }

                    override fun onFailure(call: Call<GlobalCovidResponse>, t: Throwable) {
                        ApiResponse.error(t.message.toString(), mutableListOf(resultData))
                        Log.e("RemoteDataSource", t.message.toString())
                    }

                })
        }, 1500)

        return resultData
    }

    fun getConfirmedGlobalCovid(): LiveData<ApiResponse<ConfirmedGlobalCovidResponse>> {
        val resultData = MutableLiveData<ApiResponse<ConfirmedGlobalCovidResponse>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            CovidService
                .create()
                .getGlobalCovid()
                .enqueue(object : Callback<GlobalCovidResponse> {
                    override fun onResponse(
                        call: Call<GlobalCovidResponse>,
                        response: Response<GlobalCovidResponse>
                    ) {
                        resultData.postValue(
                            response.body()
                                ?.let { ApiResponse.success(it.confirmed as ConfirmedGlobalCovidResponse) }
                        )
                    }

                    override fun onFailure(call: Call<GlobalCovidResponse>, t: Throwable) {
                        ApiResponse.error(t.message.toString(), mutableListOf(resultData))
                        Log.e("RemoteDataSource", t.message.toString())
                    }

                })
        }, 1500)

        return resultData
    }

    fun getAllIDCovid(): LiveData<ApiResponse<IDCovidItemResponse>> {
        val resultData = MutableLiveData<ApiResponse<IDCovidItemResponse>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            CovidService
                .create()
                .getIDCovid()
                .enqueue(object : Callback<List<IDCovidItemResponse>> {
                    override fun onResponse(
                        call: Call<List<IDCovidItemResponse>>,
                        response: Response<List<IDCovidItemResponse>>
                    ) {
                        ApiResponse.success(response.body()?.get(0)).let {
                            resultData.postValue(
                                it as ApiResponse<IDCovidItemResponse>
                            )
                        }
                    }

                    override fun onFailure(call: Call<List<IDCovidItemResponse>>, t: Throwable) {
                        ApiResponse.error(t.message.toString(), mutableListOf(resultData))
                        Log.e("RemoteDataSource", t.message.toString())
                    }

                })
        }, 1500)

        return resultData
    }

    //vaksinasi
    fun getAllVaccination(): LiveData<ApiResponse<List<VaccinationMonitoringItemResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<VaccinationMonitoringItemResponse>>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            VaccinationService
                .create()
                .getAllVaccination()
                .enqueue(object : Callback<VaccinationResponse> {
                    override fun onResponse(
                        call: Call<VaccinationResponse>,
                        response: Response<VaccinationResponse>
                    ) {
                        ApiResponse.success(response.body()?.monitoring).let {
                            resultData.postValue(
                                it as ApiResponse<List<VaccinationMonitoringItemResponse>>
                            )
                        }
                    }

                    override fun onFailure(call: Call<VaccinationResponse>, t: Throwable) {
                        ApiResponse.error(t.message.toString(), mutableListOf(resultData))
                        Log.e("RemoteDataSource", t.message.toString())
                    }

                })
        }, 1500)

        return resultData
    }
}

