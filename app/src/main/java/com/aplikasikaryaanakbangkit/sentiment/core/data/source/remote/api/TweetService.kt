package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api

import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.TweetResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface TweetService {

    @Headers("Authorization: OAuth oauth_consumer_key=\"TR3VIB4yHxkGUBkyjzpdpNjmn\", oauth_token=\"296643374-xgcSBExlhLxvq579FOH9911qNmm85tFMkUumnA7G\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1621937136\", oauth_nonce=\"xElSRIQhUqO\", oauth_version=\"1.0\", oauth_signature=\"wGYkaDPWf8Pzrv5fQ3Iy9DJ8Lgs%3D\"")
    @GET("search/recent?max_results=10&expansions=author_id,geo.place_id&place.fields=geo&tweet.fields=created_at,text,public_metrics,geo&query=vaksin sinovac astrazeneca covid-19&user.fields=username,profile_image_url")
    fun getAllTweet(): Call<TweetResponse>

    companion object {
        private const val BASE_URL = "https://api.twitter.com/2/tweets/"

        fun create(): TweetService {
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

            return retrofit.create(TweetService::class.java)
        }
    }
}