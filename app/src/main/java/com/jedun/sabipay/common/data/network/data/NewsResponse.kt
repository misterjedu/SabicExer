package com.jedun.sabipay.common.data.network.data

data class NewsResponse(
    val articles: List<NewsArticleDto>, val page: Int? = 0
)