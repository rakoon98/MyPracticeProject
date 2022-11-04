package com.example.test.compose.model.nav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.test.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView

sealed class BottomNavItem (
    val title : Int,
    val icon : Int,
    val screenRoute : String
) {
    object Home : BottomNavItem(R.string.homeTitle, R.drawable.ic_baseline_favorite_24, Routes.HOME)
    object Contents1 : BottomNavItem(R.string.content1Title, R.drawable.ic_baseline_favorite_24, Routes.CONTENTS1)
    object Contents2 : BottomNavItem(R.string.content2Title, R.drawable.ic_baseline_favorite_24, Routes.CONTENTS2)
    object More : BottomNavItem(R.string.moreTitle, R.drawable.ic_baseline_favorite_24, Routes.MORE)
}

val bottomList = listOf<BottomNavItem>(
    BottomNavItem.Home,
    BottomNavItem.Contents1,
    BottomNavItem.Contents2,
    BottomNavItem.More,
)

@Composable
fun BottomNavBuild(
    navController : NavHostController
) {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    when ( currentRoute ) {
        Routes.HOME,
        Routes.CONTENTS1,
        Routes.CONTENTS2,
        Routes.MORE -> true
        else -> false
    }.also { bottomBarState.value = it }

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically( initialOffsetY = { it } ),
        exit = slideOutVertically( targetOffsetY = { it } )
    ) {
        BottomNavigation {
            bottomList.forEach { bottomItem ->
                BottomNavigationItem(
                    selected = false,
                    icon = { Icon( painterResource(id = bottomItem.icon), contentDescription = null ) },
                    label = { Text(text = stringResource(id = bottomItem.title), fontSize = 16.sp) },
                    onClick = {
                        navController.navigate(bottomItem.screenRoute) {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }
}


