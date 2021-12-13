package com.jedun.sabipay.articles.presentation.articles.screens

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.jedun.sabipay.articles.presentation.articles.ArticlesViewModel
import com.jedun.sabipay.articles.presentation.articles.components.ImageCard
import com.jedun.sabipay.articles.presentation.articles.model.UiArticle

@Composable
fun ArticleListScreen(
    navController: NavController,
    viewModel: ArticlesViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val articleListItem: LazyPagingItems<UiArticle> =
        viewModel.state.value.articles.collectAsLazyPagingItems()

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
//                        navController.navigate(route = Screen.ArticleWeb.passId(encodedUrl(url)))
                    }
                )
            }
        }
    }
}

