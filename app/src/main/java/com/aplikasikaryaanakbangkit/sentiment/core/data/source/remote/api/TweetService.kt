package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api

import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet.TweetResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

interface TweetService {

    @GET("search/recent?max_results=100&expansions=author_id,geo.place_id&place.fields=geo&tweet.fields=created_at,text,public_metrics,geo,referenced_tweets&query=covid vaksin&user.fields=username,profile_image_url")
    fun getAllTweet(): Call<TweetResponse>

    companion object {
        private const val BASE_URL_TWITTER = "https://api.twitter.com/2/tweets/"
        private var retrofit: Retrofit? = null

        private fun getClient(baseUrl: String, consumer: OkHttpOAuthConsumer): Retrofit? {
            val loggingInterceptor =
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .addInterceptor(SigningInterceptor(consumer))
                    .addInterceptor { chain ->
                        val request = chain.request()
                        val requestBuilder = request.newBuilder()
                                .header(
                                        "Cookie",
                                        "guest_id=v1%3A162193671981972958; personalization_id=\"v1_i1siNpmTg+1Ao8ZnadTsWQ==\""
                                )
                        val modifiedRequest = requestBuilder.build()
                        chain.proceed(modifiedRequest)
                    }
                    .addNetworkInterceptor(loggingInterceptor)

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client.build())
                        .build()
            }
            return retrofit
        }

        fun create(): TweetService {
            val consumer = OkHttpOAuthConsumer(
                    "TR3VIB4yHxkGUBkyjzpdpNjmn",
                    "aSZi91nVk3bgXxqp2T9AhQt485GnuPaVOiceGUtRYPdMZklaRu"
            )
            consumer.setTokenWithSecret(
                    "296643374-xgcSBExlhLxvq579FOH9911qNmm85tFMkUumnA7G",
                    "7vQ9RyznRBDPlh9DG9Bb5VPbEdLz08zZK7Ugt7ErXrVUP"
            )
            return getClient(BASE_URL_TWITTER, consumer)!!
                    .create(TweetService::class.java)
        }
    }
}