package com.jedun.sabipay.articles.presentation.articles

import com.jedun.sabipay.common.domain.model.Article

data class ArticleListState(
    val articles: List<Article> = emptyList()
)
