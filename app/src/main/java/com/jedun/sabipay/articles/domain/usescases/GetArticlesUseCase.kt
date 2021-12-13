package com.jedun.sabipay.articles.domain.usescases

import androidx.paging.PagingData
import com.jedun.sabipay.common.domain.model.Article
import com.jedun.sabipay.common.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: NewsRepository,
) {
    suspend operator fun invoke(): Flow<PagingData<Article>> {
        return repository.getNews()

    }
}