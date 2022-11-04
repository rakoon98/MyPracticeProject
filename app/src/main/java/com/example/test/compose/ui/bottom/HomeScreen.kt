package com.example.test.compose.ui.bottom

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*

@Composable
fun HomeScreen() {
        Box(
            modifier = Modifier
        ) {
            Text(
                text = "홈입니다",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
}