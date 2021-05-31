package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api

import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.covid.GlobalCovidResponse
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.covid.IDCovidItemResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CovidService {

    @GET("api")
    fun getGlobalCovid(): Call<GlobalCovidResponse>

    @GET("api/countries/id/confirmed")
    fun getIDCovid(): Call<List<IDCovidItemResponse>>

    companion object {
        private const val BASE_URL = "https://covid19.mathdro.id/"

        fun create(): CovidService {
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

            return retrofit.create(CovidService::class.java)
        }
    }
}