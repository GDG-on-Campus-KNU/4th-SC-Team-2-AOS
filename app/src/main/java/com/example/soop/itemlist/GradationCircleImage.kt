package com.example.soop.itemlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.soop.R

@Composable
fun GradationCircleImage(index: Int, size: Int = 44, modifier: Modifier = Modifier) {
    val imageRes = when (index) {
        0 -> R.drawable.gradation_circle_blue
        1 -> R.drawable.gradation_circle_green
        2 -> R.drawable.gradation_circle_mint
        3 -> R.drawable.gradation_circle_orange
        4 -> R.drawable.gradation_circle_pink
        5 -> R.drawable.gradation_circle_purple
        6 -> R.drawable.gradation_circle_red
        else -> R.drawable.gradation_circle_blue // 기본값
    }

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "gradation circle",
        modifier = modifier.size(size.dp)
    )
}