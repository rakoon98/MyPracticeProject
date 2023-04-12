package com.example.test.compose.ui.contents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import io.ak1.drawbox.DrawBox
import io.ak1.drawbox.rememberDrawController

@Composable
fun CanvasCompose() {
    val controller = rememberDrawController()

    Column() {
        DrawBox(
            drawController = controller,
            modifier = Modifier.fillMaxSize().weight(1f, true),
            bitmapCallback = { imageBitmap: ImageBitmap?, throwable: Throwable? -> }
        )
    }
}