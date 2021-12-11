package com.jedun.sabipay.common.data.repository

import com.jedun.sabipay.common.data.network.NewsApi
import com.jedun.sabipay.common.domain.mappers.DomainArticleMapper
import com.jedun.sabipay.common.domain.model.Article
import com.jedun.sabipay.common.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsApiRepository @Inject constructor(
    private val api: NewsApi,
    private val mapper: DomainArticleMapper
) : NewsRepository {
    override suspend fun getNews(page: Int): Flow<List<Article>> {
        return flow {
            val articleList = api.getBreakingNews().articles.map { mapper.mapToDomain(it) }
            emit(articleList)
        }.flowOn(IO)

    }
}