package com.example.test.compose.ui.contents

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun ContentsDetail(
    navController: NavController,
    id : Int
) {

    Text(
        modifier = Modifier
            .fillMaxSize(),
        text = "$id",
        textAlign = TextAlign.Center
    )

}