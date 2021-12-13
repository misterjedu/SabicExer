package com.jedun.sabipay.articles.presentation.articles

import androidx.paging.PagingData
import com.jedun.sabipay.common.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ArticleListState(
    val articles: Flow<PagingData<Article>> = flowOf()
)
