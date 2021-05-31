package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api

import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.sentiment.SentimentResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SentimentService {

    @POST("predict")
    fun insertTweetToAnalysis(@Body tweet: String): Call<SentimentResponse>

    @GET("predict")
    fun getAnalysis(): Call<SentimentResponse>

    companion object {
        private const val BASE_URL = "http://127.0.0.1:8000/"

        fun create(): SentimentService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val request = chain.request()
                    val requestBuilder = request.newBuilder()
                        .header(
                            "accept",
                            "application/json"
                        )
                        .header(
                            "Content-Type",
                            "application/json"
                        )
                    val modifiedRequest = requestBuilder.build()
                    chain.proceed(modifiedRequest)
                }
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(SentimentService::class.java)
        }
    }
}