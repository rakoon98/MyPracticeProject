package com.example.test.compose.ui.bottom

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val scaffoldState = rememberScaffoldState()
    var textValue by rememberSaveable { mutableStateOf<String>("") }
    val scope = rememberCoroutineScope()

    androidx.compose.material.Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .padding(paddingValue)
        ) {
            Button(onClick = {

            }) {
                Text("스낵바")
            }


            androidx.compose.material.OutlinedTextField(
                value = textValue,
                onValueChange = {
                    textValue = it
                }
            )
        }
    }

    LaunchedEffect(key1 = textValue) {
        if ( textValue.isNotEmpty() ) {
            val result = scaffoldState.snackbarHostState.showSnackbar(
                message = "Current Text Is $textValue",
                duration = SnackbarDuration.Indefinite,
                actionLabel = "DoAction"
            )

            when (result) {
                SnackbarResult.ActionPerformed -> { Log.d("데이터체크", "SnackbarResult.ActionPerformed") }
                SnackbarResult.Dismissed -> { Log.d("데이터체크", "SnackbarResult.Dismissed") }
            }

            delay(500L)
            cancel()
        }
    }
}