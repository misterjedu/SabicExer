package com.jedun.sabipay.common.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jedun.sabipay.articles.presentation.articles.ArticleSource
import com.jedun.sabipay.articles.domain.model.Article
import com.jedun.sabipay.common.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsApiRepository @Inject constructor(
    private val articleSource: ArticleSource,
) : NewsRepository {
    override suspend fun getNews(): Flow<PagingData<Article>> =
        Pager(PagingConfig(pageSize = 10)) { articleSource }.flow

//    {
//        return flow {
//            val articleList = api.getBreakingNews().articles.map { mapper.mapToDomain(it) }
//            emit(articleList)
//        }.flowOn(IO)
//
//    }
}