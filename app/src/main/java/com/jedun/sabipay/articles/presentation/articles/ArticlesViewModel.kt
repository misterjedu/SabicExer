package com.jedun.sabipay.articles.presentation.articles

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jedun.sabipay.articles.domain.usescases.GetArticlesUseCase
import com.jedun.sabipay.common.domain.model.Article
import com.jedun.sabipay.common.presentation.mapper.UiArticleMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articlesUseCase: GetArticlesUseCase,
    private val mapper: UiArticleMapper,
    private val articleSource: ArticleSource,

    ) : ViewModel() {

    private var getArticlesJob: Job? = null

    private val _state = mutableStateOf(ArticleListState())

    var pagingData: Flow<PagingData<Article>> = Pager(PagingConfig(pageSize = 10)) {
        articleSource
    }.flow.cachedIn(viewModelScope)

    val state: State<ArticleListState> = _state

//    init {
//        getArticles()
//    }

//    var ppp: Flow<PagingData<Article>> =


    fun getArticles() {
        getArticlesJob?.cancel()
        getArticlesJob = viewModelScope.launch {
            pagingData = articlesUseCase()
//            _state.value = state.value.copy(
//                articles = articlesUseCase(page)
//            )
        }
    }

}