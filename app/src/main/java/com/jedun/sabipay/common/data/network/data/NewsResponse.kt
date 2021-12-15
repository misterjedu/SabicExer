package com.jedun.sabipay.common.data.network.data

data class NewsResponse(
    val articles: List<NewsArticleDto>, var page: Int? = 0
)