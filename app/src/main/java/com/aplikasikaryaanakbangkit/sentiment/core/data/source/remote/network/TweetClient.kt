package com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.network

import com.aplikasikaryaanakbangkit.sentiment.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor
import java.util.concurrent.TimeUnit

class TweetClient {
    companion object {
        private var retrofit: Retrofit? = null
        private val gSon = GsonBuilder()
            .setLenient()
            .create()

        fun getClient(baseUrl: String, consumer: OkHttpOAuthConsumer): Retrofit?{
            val loggingInterceptor = HttpLoggingInterceptor()
            if(BuildConfig.DEBUG){
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }else{
                loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            val client = OkHttpClient.Builder()
                .connectTimeout(6000, TimeUnit.SECONDS)
                .writeTimeout(120000, TimeUnit.SECONDS)
                .readTimeout(120000, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(SigningInterceptor(consumer))
                .addInterceptor{ chain ->
                    val request = chain.request()
                    val requestBuilder = request.newBuilder()
                            .header("Cookie", "guest_id=v1%3A162193671981972958; personalization_id=\"v1_i1siNpmTg+1Ao8ZnadTsWQ==\"")
                    val modifiedRequest = requestBuilder.build()
                    chain.proceed(modifiedRequest)
                }

            client.addNetworkInterceptor(loggingInterceptor)

            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gSon))
                    .client(client.build())
                    .build()
            }
            return retrofit
        }
    }
}