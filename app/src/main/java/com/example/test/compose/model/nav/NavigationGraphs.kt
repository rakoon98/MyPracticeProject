package com.example.test.compose.model.nav

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.test.compose.ui.bottom.Content1Screen
import com.example.test.compose.ui.bottom.ContentsMainScreen
import com.example.test.compose.ui.bottom.HomeScreen
import com.example.test.compose.ui.bottom.MoreScreen
import com.example.test.compose.ui.contents.*
import com.example.test.data.model.PersonModel
import com.example.test.viewmodel.SharedViewModel

@Composable
fun ComposeNavigation(navController: NavHostController) {

    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Graphs.BOTTOM
    ) {
//        composable(route = Routes.HOME) { HomeScreen() }
        bottomNavigationGraph(navController, sharedViewModel)
        contentNavigationGraph(
            navController,
            sharedViewModel
        )
    }
}


fun NavGraphBuilder.bottomNavigationGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    navigation(startDestination = Routes.CONTENTS_MAIN, route = Graphs.BOTTOM) {
        composable(route = Routes.HOME) { HomeScreen() }
        composable(route = Routes.CONTENTS1) { Content1Screen(navController) }
        composable(route = Routes.CONTENTS_MAIN) { ContentsMainScreen(navController, sharedViewModel) }
        composable(route = Routes.MORE) { MoreScreen() }
    }
}


fun NavGraphBuilder.contentNavigationGraph(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {

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
        composable(route = Routes.ITEMS_ANIMATOR) { ItemAnimatorLazyColumn() }
        composable(route = Routes.LOADING_ANIMATION) { LoadingAnimationScreen() }
        composable(route = Routes.SELECTABLE_ITEM) { SelectableItemScreen() }
        composable(route = Routes.CANVAS) { CanvasCompose() }
        composable(route = Routes.PARCELABLE) {
//            LaunchedEffect(key1 = it, block = {
//                val result = navController.previousBackStackEntry?.savedStateHandle?.get<PersonModel>("person")
//                Log.d("PARCELABLE 객체","$result")
//            })

//            val result = navController.previousBackStackEntry?.savedStateHandle?.get<PersonModel>("person")
//                as PersonModel
//            Log.d("PARCELABLE 객체","$result")
//            ParcelableScreen(result)
            ParcelableScreen(sharedViewModel)

        }

    }
}