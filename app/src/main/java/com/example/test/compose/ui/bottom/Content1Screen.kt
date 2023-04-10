package com.example.test.compose.ui.bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.test.compose.model.nav.Graphs
import com.example.test.compose.model.nav.Routes
import com.example.test.viewmodel.ContentViewModel

@Composable
fun Content1Screen(
    navController: NavController,
    contentViewModel : ContentViewModel = viewModel()
) {
    val collectSelectedIndex = contentViewModel.selectedIndex.collectAsState(-1)
    val lazyListState = rememberLazyListState()
    // used to obtain initial offsets on drag start
    var initiallyDraggedElement by remember {mutableStateOf<LazyListItemInfo?>(null) }
    var currentIndexOfDraggedItem by remember { mutableStateOf<Int?>(null) }

//    val dragDropListState = rememberDragDropListState(onMove = onMove)

    LaunchedEffect(Unit) {
        contentViewModel.getAlarmListInViewModel()
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (list, btn) = createRefs()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(list) {}
                .pointerInput(Unit) {
//                    detectDragGesturesAfterLongPress(
//                        onDrag = { change, offset ->
//
//                        },
//                        onDragStart = { offset ->  },
//                        onDragCancel = {  },
//                        onDragEnd = {  }
//                    )
                }
        ) {
            items(1000) { index ->
                ListItem(
                    navController,
                    contentViewModel,
                    collectSelectedIndex.value,
                    index
                )
            }
        }

        Button(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(btn) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onClick = {
                navController.navigate( "${Routes.CONTENT_DETAIL}/${collectSelectedIndex.value}" )
            },
        ) {

            Text(
                modifier = Modifier.wrapContentSize(),
                text  = "이동"
            )
        }
    }
}

@Composable
fun ListItem(
    navController: NavController,
    contentViewModel : ContentViewModel,
    selectedIndex : Int,
    index : Int
) {
    Text(
        text = "$index",
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                if (selectedIndex == index) Color.Yellow else Color.White
            )
            .clickable {
                contentViewModel.selectIndex(index)
            }
            .padding(20.dp),
        color = if ( selectedIndex == index ) Color.Cyan else Color.Black,
        fontSize = 24.sp
    )
}


@Composable
@Preview
fun ListPreView() {
    val controller = rememberNavController()

    ListItem(
        controller,
        viewModel(),
        -1,
        1000
    )
}

