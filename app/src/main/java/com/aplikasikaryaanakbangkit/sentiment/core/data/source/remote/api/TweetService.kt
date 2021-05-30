package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api

import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.tweet.TweetResponse
import retrofit2.Call
import retrofit2.http.GET

interface TweetService {

    @GET("search/recent?max_results=100&expansions=author_id,geo.place_id&place.fields=geo&tweet.fields=created_at,text,public_metrics,geo,referenced_tweets&query=covid vaksin sinovac astrazeneca&user.fields=username,profile_image_url")
    fun getAllTweet(): Call<TweetResponse>
}