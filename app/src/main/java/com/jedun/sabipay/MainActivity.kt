package com.jedun.sabipay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jedun.sabipay.articles.presentation.SetUpNavGraph
import com.jedun.sabipay.ui.theme.SabiPayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SabiPayTheme {
                navController = rememberNavController()
                SetUpNavGraph(navController = navController)

                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    ArticleListScreen()
//                }
            }
        }

    }
}
