package com.example.test.compose.model.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.test.compose.ui.bottom.Content1Screen
import com.example.test.compose.ui.bottom.Content2Screen
import com.example.test.compose.ui.bottom.HomeScreen
import com.example.test.compose.ui.bottom.MoreScreen
import com.example.test.compose.ui.contents.ContentsDetail
import com.example.test.compose.ui.contents.ViewPagerScreen
import kotlinx.serialization.builtins.serializer

@Composable
fun ComposeNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Graphs.BOTTOM
    ) {
//        composable(route = Routes.HOME) { HomeScreen() }
        bottomNavigationGraph(navController)
        contentNavigationGraph(navController)
    }
}


fun NavGraphBuilder.bottomNavigationGraph(navController: NavHostController) {
    navigation(startDestination = Routes.HOME, route = Graphs.BOTTOM) {
        composable(route = Routes.HOME) { HomeScreen() }
        composable(route = Routes.CONTENTS1) { Content1Screen(navController) }
        composable(route = Routes.CONTENTS2) { Content2Screen(navController) }
        composable(route = Routes.MORE) { MoreScreen() }
    }
}

fun NavGraphBuilder.contentNavigationGraph(navController: NavController) {
    navigation(startDestination = Routes.CONTENT_DETAIL, route = Graphs.CONTENT) {
        composable(
            route = "${Routes.CONTENT_DETAIL}/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
        ) {
            ContentsDetail(navController, it.arguments?.getInt("id",-1) ?: -1)
        }
        composable(route = Routes.VIEW_PAGER) { ViewPagerScreen(navController) }
    }
}