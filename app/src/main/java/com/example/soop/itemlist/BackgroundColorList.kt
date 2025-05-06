package com.example.soop.itemlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.soop.R

@Composable
fun BackgroundColorList(): List<Color> {
    return listOf(
        colorResource(id = R.color.background_color1),
        colorResource(id = R.color.background_color2),
        colorResource(id = R.color.background_color3),
        colorResource(id = R.color.background_color4),
    )
}

@Composable
fun HomeBackgroundColorList(): List<Color> {
    return listOf(
        colorResource(id = R.color.home_background_color1),
        colorResource(id = R.color.home_background_color2),
        colorResource(id = R.color.home_background_color3),
    )
}
