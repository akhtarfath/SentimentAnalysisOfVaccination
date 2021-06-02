package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api

import com.aplikasikaryaanakbangkit.sentiment.BuildConfig
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.news.NewsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsService {

    @GET("top-headlines?q=covid&country=id&category=health&apiKey=${BuildConfig.NEWS_API_KEY}")
    fun getCovidHeadlines(): Call<NewsResponse>

    @GET("everything?q=vaksin&apiKey=${BuildConfig.NEWS_API_KEY}")
    fun getVaccineNews(): Call<NewsResponse>

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"

        fun create(): NewsService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(NewsService::class.java)
        }
    }
}