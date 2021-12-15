package com.jedun.sabipay.articles.presentation.articles


const val ARTICLE_URL = "url"


sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object ArticleWeb : Screen(route = "article_web_screen/{$ARTICLE_URL}") {
        fun passId(url: String): String {
            return this.route.replace(oldValue = "{$ARTICLE_URL}", newValue = url)
        }
    }
}
