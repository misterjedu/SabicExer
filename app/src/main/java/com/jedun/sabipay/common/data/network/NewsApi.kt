package com.jedun.sabipay.common.data.network

import com.jedun.sabipay.common.data.network.NetworkConstants.API_KEY
import com.jedun.sabipay.common.data.network.data.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("page")
        pageNumber: Int = 1,
        @Query("country")
        countryCode: String = "us",
        @Query("apiKey")
        apiKey: String = API_KEY
    ): NewsResponse

}