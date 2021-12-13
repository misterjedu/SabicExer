package com.jedun.sabipay.articles.presentation.articles.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jedun.sabipay.articles.presentation.articles.ArticlesViewModel

@Composable
fun Nuts(
    viewModel: ArticlesViewModel = hiltViewModel()
) {
    println(viewModel.state.value)
}