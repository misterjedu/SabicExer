package com.jedun.sabipay.articles.presentation.articles

sealed class ArticleListEvent {
    data class GetArticles(val page: Int) : ArticleListEvent()
}
