package com.jedun.sabipay.articles.presentation.articles.screens

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController


@Composable
fun ArticleWebScreen(
    navController: NavController,
    url: String,
) {

    val customIntent = CustomTabsIntent.Builder()

    val customTabsIntent = customIntent.build()

    customTabsIntent.launchUrl(LocalContext.current, Uri.parse(url))

}