package com.jedun.sabipay.articles.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jedun.sabipay.articles.presentation.articles.ARTICLE_URL
import com.jedun.sabipay.articles.presentation.articles.Screen
import com.jedun.sabipay.articles.presentation.articles.screens.ArticleListScreen
import com.jedun.sabipay.articles.presentation.articles.screens.ArticleWebScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            ArticleListScreen(navController = navController)
        }

        composable(
            route = Screen.ArticleWeb.route,
            arguments = listOf(navArgument(ARTICLE_URL) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString(ARTICLE_URL).let { url ->
                url?.let {
                    ArticleWebScreen(navController, it)
                }
            }
        }
    }
}