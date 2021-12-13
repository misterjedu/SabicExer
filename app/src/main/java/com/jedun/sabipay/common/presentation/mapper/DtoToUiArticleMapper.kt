package com.jedun.sabipay.common.presentation.mapper

import com.jedun.sabipay.common.data.network.data.NewsArticleDto
import com.jedun.sabipay.common.presentation.model.UiArticle
import com.jedun.sabipay.common.utils.iDomainMapper
import javax.inject.Inject


class DtoToUiArticleMapper @Inject constructor() : iDomainMapper<NewsArticleDto, UiArticle> {

    override fun mapToDomain(entity: NewsArticleDto): UiArticle {
        return UiArticle(
            title = entity.title,
            url = entity.url,
            author = entity.author,
            urlToImage = entity.urlToImage
        )
    }

}