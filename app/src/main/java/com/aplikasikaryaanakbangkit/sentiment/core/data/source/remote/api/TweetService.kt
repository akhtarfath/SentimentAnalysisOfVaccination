package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api

import com.aplikasikaryaanakbangkit.sentiment.BuildConfig
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

        private fun String.getClient(
                consumer: OkHttpOAuthConsumer
        ): Retrofit? {
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
                        .baseUrl(this)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client.build())
                        .build()
            }
            return retrofit
        }

        fun create(): TweetService {
            val consumer = OkHttpOAuthConsumer(
                    BuildConfig.TWITTER_CONSUMER_KEY,
                    BuildConfig.TWITTER_CONSUMER_SECRET
            )
            consumer.setTokenWithSecret(
                    BuildConfig.TWITTER_TOKEN,
                    BuildConfig.TWITTER_TOKEN_SECRET
            )
            return BASE_URL_TWITTER.getClient(consumer)!!
                    .create(TweetService::class.java)
        }
    }
}