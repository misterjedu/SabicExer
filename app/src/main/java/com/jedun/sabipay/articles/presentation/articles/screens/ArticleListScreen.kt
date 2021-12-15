package com.jedun.sabipay.articles.presentation.articles.screens

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.jedun.sabipay.R
import com.jedun.sabipay.articles.presentation.articles.ArticlesViewModel
import com.jedun.sabipay.articles.presentation.articles.components.ImageCard
import com.jedun.sabipay.articles.presentation.articles.model.UiArticle
import com.jedun.sabipay.common.presentation.ui.pagingstates.ErrorItem
import com.jedun.sabipay.common.presentation.ui.pagingstates.LoadingItem
import com.jedun.sabipay.common.presentation.ui.pagingstates.LoadingView
import com.jedun.sabipay.ui.theme.Dark100
import com.jedun.sabipay.ui.theme.Dark300
import kotlinx.coroutines.flow.Flow

@Composable
fun ArticleListScreen(
    navController: NavController,
    viewModel: ArticlesViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.news_feed), color = Color.White)
                },
                backgroundColor = Dark100
            )
        },
        content = {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(color = Dark300)
            ) {
                Text(
                    text = stringResource(R.string.top_news),
                    modifier = Modifier.padding(10.dp),
                    color = Color.White
                )
                ArticleScreen(listItem = viewModel.state.value.articles)
            }

        }
    )
}


@Composable
fun ArticleScreen(listItem: Flow<PagingData<UiArticle>>) {
    val context = LocalContext.current
    val articleListItem: LazyPagingItems<UiArticle> = listItem.collectAsLazyPagingItems()

    LazyColumn {
        items(
            items = articleListItem,
        ) { article ->
            article?.let { it ->
                ImageCard(
                    imageUrl = it.urlToImage,
                    author = it.author,
                    title = it.title,
                    url = it.url,
                    onClick = { url ->
                        val customIntent = CustomTabsIntent.Builder()
                        val customTabsIntent = customIntent.build()
                        customTabsIntent.launchUrl(context, Uri.parse(url))
                    }
                )
            }
        }

        articleListItem.apply {
            when {
                loadState.refresh is
                        LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is
                        LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is
                        LoadState.Error -> {
                    val exception = articleListItem.loadState.refresh as LoadState.Error

                    item {
                        ErrorItem(
                            message = exception.error.localizedMessage ?: "",
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is
                        LoadState.Error -> {
                    val exception = articleListItem.loadState.append as LoadState.Error

                    item {
                        ErrorItem(
                            message = exception.error.localizedMessage ?: "",
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}