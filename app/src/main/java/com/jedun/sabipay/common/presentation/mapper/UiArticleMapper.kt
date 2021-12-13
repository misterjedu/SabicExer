package com.jedun.sabipay.common.presentation.mapper

import com.jedun.sabipay.common.domain.model.Article
import com.jedun.sabipay.common.presentation.model.UiArticle
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