package com.example.test.compose.ui.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetsnack.ui.theme.randomColor
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.absoluteValue

@Composable
fun ViewPagerScreen(
    navController: NavController
) {
    HorizontalViewPagerWithTap()
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalViewPager(
    count : Int,
    state : PagerState
) {
    HorizontalPager(
        count = count,
        state = state,
        // horizontal mean carousel start with end
        // start mean carousel start & end mean carousel end
        contentPadding = PaddingValues(
//            start = 32.dp,
//            end = 32.dp
            horizontal = 32.dp
        ),
        modifier = Modifier.fillMaxSize()
    ) { index ->
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .padding(48.dp)
                .background(color = randomColor())
                .fillMaxSize()
                .graphicsLayer {
                    /** 캐러셀 적용하는 방법 ... 와  */
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(index).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
//                    lerp(
//                        start = 0.75f,
//                        stop = 1f,
//                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                    ).also { scale ->
//                        scaleX = scale
//                        scaleY = scale
//                    }

                    // We animate the alpha, between 50% and 100%
//                    alpha = lerp(
//                        start = 0.5f,
//                        stop = 1f,
//                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                    )
                }
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            // Card content
            Text(
                text = "${index} 페이지 입니다.",
                modifier = Modifier.wrapContentSize()
            )
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalViewPagerWithTap() {
    val current = LocalContext.current

    val tabTitles = listOf<String>("First", "Second", "Third")
    val pagerState = rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()

    Column(
         modifier = Modifier
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPosition ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPosition)
                )
            }
        ) {
            tabTitles.forEachIndexed { index, string ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        /** Column 같은 부모 View로 감싸줘야 작동함 */
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text("$string.") }
                )
            }
        }
        HorizontalViewPager(tabTitles.size, pagerState)
    }



    LaunchedEffect(Unit) {}
}
