package com.example.test.compose.ui.contents

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 *  androidx.compose.animation:animation:1.3.3 사용
 *  https://developer.android.com/jetpack/compose/animation?hl=ko 참고
 *      - keyframes
 */

@Composable
fun LoadingAnimationScreen() {
    LoadingAnimation()
}

@Composable
fun LoadingAnimation(
    modifier : Modifier = Modifier,
    circleSize : Dp = 25.dp,
    circleColor: Color = MaterialTheme.colors.primary,
    spaceBetween : Dp = 10.dp,
    travelDistance : Dp = 20.dp
) {
    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 200L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
//                        0.0f at 0 with LinearOutSlowInEasing // for 0-15 ms
//                        0.2f at 15 with FastOutLinearInEasing // for 15-75 ms
//                        0.4f at 75 // ms
//                        0.4f at 225 // ms

//                        0.0f at 0 with LinearOutSlowInEasing
//                        1.0f at 300 with LinearOutSlowInEasing
//                        0.0f at 600 with LinearOutSlowInEasing
//                        0.0f at 1200 with LinearOutSlowInEasing

                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 0 with FastOutLinearInEasing
//                        0.0f at 1000
//                        0.0f at 3000
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }
    val lastCircle = circleValues.size - 1

    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
//        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        circleValues.forEachIndexed { index, value ->
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .graphicsLayer {
                        translationY = -value * distance
                    }
                    .background(
                        color = circleColor,
                        shape = CircleShape
                    )

            )


            if ( index != lastCircle ) {
                Spacer(modifier = Modifier.width(spaceBetween))
            }
        }
    }
}