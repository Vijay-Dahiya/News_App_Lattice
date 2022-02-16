package com.vijay.newsapp.model.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This is our main retrofit class used to call network data using path stored in our api interface.
 */
class Network {
    companion object {

        private const val BASE_URL = "https://newsapi.org/"
        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build()
        }

        val apiClient: ApiClient = getRetrofit().create(ApiClient::class.java)
    }
}