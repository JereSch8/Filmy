@file:OptIn(ExperimentalMaterial3Api::class)

package com.peakycoders.filmy.ui.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerAnimationHorizontal(){
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier.horizontalScroll(scrollState)
    ) {
        ShimmerItemHorizontal(brush = brush)
        ShimmerItemHorizontal(brush = brush)
        ShimmerItemHorizontal(brush = brush)
        ShimmerItemHorizontal(brush = brush)
    }
}

@Composable
fun ShimmerAnimationVertical(){
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        ShimmerItemVertical(brush = brush)
        ShimmerItemVertical(brush = brush)
        ShimmerItemVertical(brush = brush)
        ShimmerItemVertical(brush = brush)
    }
}

@Composable
fun ShimmerItemHorizontal( brush: Brush ) {
    Column(
        modifier = Modifier
            .height(240.dp)
            .width(160.dp)
            .padding(5.dp)
            .background(color = Color.Transparent)
    ){
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = Color.Transparent),
                shape = RoundedCornerShape(20.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = brush)
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(top = 8.dp, start = 1.dp, end = 1.dp)
                    .background(brush = brush)
            )
        }
    }
}

@Composable
fun ShimmerItemVertical( brush: Brush ) {
    Row(
        modifier = Modifier
            .height(240.dp)
            .fillMaxWidth()
            .padding(5.dp)
            .background(color = Color.Transparent)
    ) {
        Card(
            modifier = Modifier
                .width(160.dp)
                .height(200.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = brush)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column{
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(
                modifier = Modifier
                    .height(35.dp)
                    .fillMaxWidth()
                    .background(brush = brush)
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Spacer(
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth()
                    .background(brush = brush)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

val ShimmerColorShades = listOf(
    Color.LightGray.copy(0.9f),
    Color.LightGray.copy(0.2f),
    Color.LightGray.copy(0.9f)
)