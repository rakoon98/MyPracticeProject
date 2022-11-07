package com.example.test.compose.ui.bottom

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.test.R
import com.example.test.compose.model.nav.Routes

@Composable
fun Content2Screen(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (col, row) = createRefs()

        /** Column 을 사용 */
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(col) {}
        ){
            Text(text = "this is Column")
            DropDownTest()
        }

        /** Row 를 사용 */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(row) {
                    top.linkTo(col.bottom)
                }
        ){
            Text(text = stringResource(id = R.string.app_name))
            Button(
                onClick = { navController.navigate(Routes.VIEW_PAGER) }
            ) {
                Text(text = "뷰페이저 스크린으로")
            }
        }


    }
}

@Composable
fun DropDownTest() {
    var expanded by remember { mutableStateOf(false) }

    Button(
        onClick = { expanded = !expanded }
    ) {
        Text(text = "Show DropDownMenu")
    }

    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, offset = DpOffset(0.dp, 12.dp)) {
        DropdownMenuItem(text = { Text(text = "첫번째 아이템") }, onClick = { expanded = false })
        DropdownMenuItem(text = { Text(text = "두번째 아이템") }, onClick = { expanded = false })
        DropdownMenuItem(text = { Text(text = "세번째 아이템") }, onClick = { expanded = false })
    }
}