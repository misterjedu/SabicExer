package com.jedun.sabipay.articles.domain.usescases

import com.jedun.sabipay.common.domain.model.Article
import com.jedun.sabipay.common.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: NewsRepository,
) {
    suspend operator fun invoke(page: Int): Flow<List<Article>> {
        return repository.getNews(page)
    }
}