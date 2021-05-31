package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.network

import com.aplikasikaryaanakbangkit.sentiment.BuildConfig
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api.TweetService
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer

class TweetUtils {
    companion object {
        private const val BASE_URL_TWITTER = "https://api.twitter.com/2/tweets/"

        fun getApiService(): TweetService {
            val consumer = OkHttpOAuthConsumer(
                BuildConfig.TWITTER_CONSUMER_KEY,
                BuildConfig.TWITTER_CONSUMER_SECRET
            )
            consumer.setTokenWithSecret(
                BuildConfig.TWITTER_TOKEN,
                BuildConfig.TWITTER_TOKEN_SECRET
            )
            return TweetClient.getClient(BASE_URL_TWITTER, consumer)!!
                .create(TweetService::class.java)
        }
    }
}