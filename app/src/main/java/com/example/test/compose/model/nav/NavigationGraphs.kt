package com.example.test.compose.model.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.test.compose.ui.bottom.Content1Screen
import com.example.test.compose.ui.bottom.Content2Screen
import com.example.test.compose.ui.bottom.HomeScreen
import com.example.test.compose.ui.bottom.MoreScreen
import kotlinx.serialization.builtins.serializer

@Composable
fun ComposeNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Graphs.BOTTOM
    ) {
//        composable(route = Routes.HOME) { HomeScreen() }
        bottomNavigationGraph()
    }
}


fun NavGraphBuilder.bottomNavigationGraph() {
    navigation(startDestination = Routes.HOME, route = Graphs.BOTTOM) {
        composable(route = Routes.HOME) { HomeScreen() }
        composable(route = Routes.CONTENTS1) { Content1Screen() }
        composable(route = Routes.CONTENTS2) { Content2Screen() }
        composable(route = Routes.MORE) { MoreScreen() }
    }
}