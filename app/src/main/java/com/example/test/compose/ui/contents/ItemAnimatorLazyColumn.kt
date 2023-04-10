package com.example.test.compose.ui.contents

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemAnimatorLazyColumn() {
    var items by remember {
        mutableStateOf(
            listOf(
                "Kotlin",
                "Java",
                "Python",
                "GO",
                "C++"
            )
        )
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = items, key = { it }) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.LightGray.copy(alpha = .2f))
                    .padding(24.dp)
                    .animateItemPlacement(animationSpec = tween(durationMillis = 200)),
                text= "I Love $it",
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold
            )


        }
        item {
            Button(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 24.dp),
                onClick = { items = items.shuffled() }
            ) {
                Text(text = "shuffled")
            }
        }

    }
}