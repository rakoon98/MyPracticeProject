package com.example.test.compose.ui.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.test.data.model.PersonModel
import com.example.test.viewmodel.SharedViewModel

@Composable
fun ParcelableScreen(
    //person : PersonModel,
    sharedViewModel: SharedViewModel
) {
    val collectPerson = sharedViewModel.personFlow.collectAsState(null)
//    val collectNewPerson = remember { mutableStateOf("") }
    var collectNewPerson by rememberSaveable { mutableStateOf<String>("") }

    Column() {
        Text(text = collectPerson.value?.name ?: "없음")

//        TextField(
//            modifier = Modifier,
//            colors = TextFieldDefaults.colors(
//                disabledContainerColor = Color.White
//            ),
//            value = collectNewPerson,
//            onValueChange = {
//                collectNewPerson = it
//            }
//        )
        androidx.compose.material.OutlinedTextField(
            // backGround 및 border 변경
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .border(
                    width = 1.dp,
                    color = Color.Cyan,
                    shape = RoundedCornerShape(22.dp)
                )
                .background(Color.Cyan, RoundedCornerShape(22.dp)),
            shape = RoundedCornerShape(22.dp),
            value = collectNewPerson,
            onValueChange = {
                collectNewPerson = it
            },
        )
        Button(onClick = { sharedViewModel.changePerson(PersonModel(collectNewPerson)) }) {
            Text(text = "사용자 이름 변경")
        }
    }
}