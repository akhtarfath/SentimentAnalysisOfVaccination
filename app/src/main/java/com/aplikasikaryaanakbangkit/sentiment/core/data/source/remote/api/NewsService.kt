package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api

import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.NewsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsService {

    @GET("top-headlines?q=covid&country=id&apiKey=3b0fc47a38bc48a9bfe8cff5dbcfd67c")
    fun getCovidHeadlines(): Call<NewsResponse>

    @GET("everything?q=vaksinasi&apiKey=3b0fc47a38bc48a9bfe8cff5dbcfd67c")
    fun getVaccineNews(): Call<NewsResponse>

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"

        fun create(): NewsService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
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