package com.jedun.sabipay.articles.presentation.articles

import androidx.paging.PagingData
import com.jedun.sabipay.articles.presentation.articles.model.UiArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ArticleListState(
    val articles: Flow<PagingData<UiArticle>> = flowOf()
)
