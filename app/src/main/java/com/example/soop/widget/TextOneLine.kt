package com.example.soop.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun TextOneLine(text:String, color:Color, fontSize:Int, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(
            color = color,
            fontSize = fontSize.sp,
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}