package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.api

import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.TweetResponse
import retrofit2.Call
import retrofit2.http.GET

interface TweetService {

    @GET("search/recent?max_results=25&expansions=author_id,geo.place_id&place.fields=geo&tweet.fields=created_at,text,public_metrics,geo&query=vaksin sinovac astrazeneca covid-19&user.fields=username,profile_image_url")
    fun getAllTweet(): Call<TweetResponse>
}