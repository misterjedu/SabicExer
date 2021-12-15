package com.jedun.sabipay.articles.presentation.articles.mapper

import com.jedun.sabipay.articles.domain.model.Article
import com.jedun.sabipay.articles.presentation.articles.model.UiArticle
import com.jedun.sabipay.common.presentation.utils.UiMapper
import javax.inject.Inject

class UiArticleMapper @Inject constructor() : UiMapper<Article, UiArticle> {

    override fun mapToUi(domain: Article): UiArticle {
        return UiArticle(
            title = domain.title,
            url = domain.url,
            author = domain.author,
            urlToImage = domain.urlToImage
        )
    }
}