package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.network

import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api.TweetService
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer

class TweetUtils {
    companion object {
        private const val BASE_URL_TWITTER = "https://api.twitter.com/2/tweets/"

        fun getApiService(): TweetService {
            val consumer = OkHttpOAuthConsumer(
                "TR3VIB4yHxkGUBkyjzpdpNjmn",
                "aSZi91nVk3bgXxqp2T9AhQt485GnuPaVOiceGUtRYPdMZklaRu"
            )
            consumer.setTokenWithSecret(
                "296643374-xgcSBExlhLxvq579FOH9911qNmm85tFMkUumnA7G",
                "7vQ9RyznRBDPlh9DG9Bb5VPbEdLz08zZK7Ugt7ErXrVUP"
            )
            return TweetClient.getClient(BASE_URL_TWITTER, consumer)!!
                .create(TweetService::class.java)
        }
    }
}