package com.jedun.sabipay.articles.presentation.articles.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.jedun.sabipay.R

@Composable
fun ImageCard(
    imageUrl: String?,
    author: String?,
    title: String?,
    url: String?,
    onClick: (url: String) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(url.orEmpty())
            }
    ) {
        Box(modifier = Modifier.height(200.dp)) {

            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.placeholder)
                    }),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = modifier
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(title.orEmpty(), style = TextStyle(color = Color.White, fontSize = 14.sp))
                    Text(
                        author.orEmpty(),
                        style = TextStyle(color = Color.White, fontSize = 10.sp)
                    )
                }
            }
        }
    }
}