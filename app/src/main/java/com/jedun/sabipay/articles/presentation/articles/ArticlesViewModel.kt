package com.jedun.sabipay.articles.presentation.articles

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jedun.sabipay.articles.domain.usescases.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articlesUseCase: GetArticlesUseCase
) : ViewModel() {

    private var getArticlesJob: Job? = null

    private val _state = mutableStateOf(ArticleListState())
    val state: State<ArticleListState> = _state


    init {
        getArticles(1)
    }

    fun getArticles(page: Int) {
        getArticlesJob?.cancel()
        getArticlesJob = viewModelScope.launch {
            articlesUseCase(page).collect { articles ->
                _state.value = state.value.copy(
                    articles = articles
                )
            }
        }
    }

}