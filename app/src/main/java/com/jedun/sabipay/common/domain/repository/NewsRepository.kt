package com.jedun.sabipay.common.domain.repository

import com.jedun.sabipay.common.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(page: Int = 1): Flow<List<Article>>

}