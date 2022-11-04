package com.example.test.compose.ui.bottom

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*

@Composable
fun Content1Screen() {
        Box(
            modifier = Modifier
        ) {
            Text(
                text = "컨텐츠1 입니다",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
}