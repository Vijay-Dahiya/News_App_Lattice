package com.vijay.newsapp.model.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This interface contains is used to store endpoints of url.
 * It stores the path of data stored on News.org which is required to our app.
 * This is a part of Retrofit operation.
 *
 */

interface ApiClient {

    companion object {
        private const val key = "dfade47304c143d2a91f54a40d13531e"
    }
@GET("v2/everything?q=bitcoin&apiKey=$key&pageSize=100")

suspend fun getData(@Query("sortBy")sortBy:String="publishedAt"): ResponseModel
}