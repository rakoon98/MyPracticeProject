package com.example.test.compose.ui.bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.test.compose.model.nav.Routes

@Composable
fun ContentsMainScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DropDownSample()
//        SameButton("DropDownTest") { expanded = !expanded }
//        /** 드랍다운 샘플 */
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            offset = DpOffset(0.dp, 12.dp)
//        ) {
//            DropdownMenuItem(text = { Text(text = "첫번째 아이템") }, onClick = { expanded = false })
//            DropdownMenuItem(text = { Text(text = "두번째 아이템") }, onClick = { expanded = false })
//            DropdownMenuItem(text = { Text(text = "세번째 아이템") }, onClick = { expanded = false })
//        }



        SameButton("Go To ViewPager Screen") { navController.navigate(Routes.VIEW_PAGER) }
        SameButton("Go To ItemsAnimator Screen") { navController.navigate(Routes.ITEMS_ANIMATOR) }
    }


}

@Composable
fun SameButton(text: String, action: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(top = 12.dp),
        onClick = { action.invoke() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Magenta,
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = text,
            color = Color.Black,
            fontSize = 14.sp,
        )
    }
}

/** 같은 레이아웃 안에 있어야 바로 아래서 나옴. */
@Composable
fun DropDownSample() {
    var expanded by remember { mutableStateOf(false) }
    Box {
        SameButton("DropDownTest") { expanded = !expanded }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, offset = DpOffset(0.dp, 12.dp)) {
            DropdownMenuItem(text = { Text(text = "첫번째 아이템") }, onClick = { expanded = false })
            DropdownMenuItem(text = { Text(text = "두번째 아이템") }, onClick = { expanded = false })
            DropdownMenuItem(text = { Text(text = "세번째 아이템") }, onClick = { expanded = false })
        }
    }
}


// 그저 예전 기록

//    ConstraintLayout(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        val (col, row) = createRefs()
//
//        /** Column 을 사용 */
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentHeight()
//                .constrainAs(col) {}
//        ){
//            Text(text = "this is Column")
//            DropDownTest()
//        }
//
//        /** Row 를 사용 */
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentHeight()
//                .constrainAs(row) {
//                    top.linkTo(col.bottom)
//                }
//        ){
//            Text(text = stringResource(id = R.string.app_name))
//            Button(
//                onClick = { navController.navigate(Routes.VIEW_PAGER) }
//            ) {
//                Text(text = "뷰페이저 스크린으로")
//            }
//        }
//    }