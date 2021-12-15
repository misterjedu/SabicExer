package com.jedun.sabipay.common.domain.repository

import androidx.paging.PagingData
import com.jedun.sabipay.articles.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(): Flow<PagingData<Article>>

}