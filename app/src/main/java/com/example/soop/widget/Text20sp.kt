package com.example.soop.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Text20sp(
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
) {
    Text(
        text = text,
        style = TextStyle(
            color = color,
            fontSize = 20.sp,
            textAlign = textAlign
        ),
        modifier = modifier
    )
}