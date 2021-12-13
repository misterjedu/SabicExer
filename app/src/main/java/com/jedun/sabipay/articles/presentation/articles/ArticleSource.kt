package com.jedun.sabipay.articles.presentation.articles

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jedun.sabipay.common.data.network.NewsApi
import com.jedun.sabipay.articles.domain.mappers.DomainArticleMapper
import com.jedun.sabipay.articles.domain.model.Article
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ArticleSource @Inject constructor(
    private val apiService: NewsApi,
    private val dtoToDomainMapper: DomainArticleMapper
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        return try {
            val nextPage = params.key ?: 1
            val articleList = apiService.getBreakingNews(nextPage)
            articleList.page = nextPage
            LoadResult.Page(
                data = articleList.articles.map { dtoToDomainMapper.mapToDomain(it) },
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (articleList.articles.isEmpty()) null else articleList.page?.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}