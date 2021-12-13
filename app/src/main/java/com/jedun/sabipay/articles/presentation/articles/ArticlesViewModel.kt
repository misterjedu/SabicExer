package com.jedun.sabipay.articles.presentation.articles

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.jedun.sabipay.articles.domain.usescases.GetArticlesUseCase
import com.jedun.sabipay.articles.presentation.articles.mapper.UiArticleMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articlesUseCase: GetArticlesUseCase,
    private val uiMapper: UiArticleMapper
) : ViewModel() {

    private var getArticlesJob: Job? = null
    private val _state = mutableStateOf(ArticleListState())
    val state: State<ArticleListState> = _state

    init {
        getArticles()
    }

    private fun getArticles() {
        getArticlesJob?.cancel()
        getArticlesJob = viewModelScope.launch {
            _state.value = state.value.copy(
                articles = articlesUseCase().map { pagingData ->
                    pagingData.map { article ->
                        uiMapper.mapToUi(article)
                    }
                }
            )
        }
    }
}