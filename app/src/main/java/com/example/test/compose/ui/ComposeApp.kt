package com.example.test.compose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.test.compose.model.nav.BottomNavBuild
import com.example.test.compose.model.nav.ComposeNavigation
import com.example.test.compose.theme.MyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeApp() {
    MyTheme {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = { BottomNavBuild(navController) }
        ) { sPadding ->
            ComposeNavigation(navController)
        }
    }
}