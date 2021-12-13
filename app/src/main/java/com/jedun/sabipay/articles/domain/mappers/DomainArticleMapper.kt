package com.jedun.sabipay.articles.domain.mappers

import com.jedun.sabipay.common.data.network.data.NewsArticleDto
import com.jedun.sabipay.articles.domain.model.Article
import com.jedun.sabipay.common.domain.utils.iDomainMapper
import javax.inject.Inject

class DomainArticleMapper @Inject constructor() : iDomainMapper<NewsArticleDto, Article> {

    override fun mapToDomain(entity: NewsArticleDto): Article {
        return Article(
            title = entity.title,
            url = entity.url,
            author = entity.author,
            urlToImage = entity.urlToImage
        )
    }

}