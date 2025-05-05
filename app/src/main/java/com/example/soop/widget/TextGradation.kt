package com.example.soop

import androidx.annotation.ColorInt
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun GradientText(
    text: String,
    @ColorInt startColor: Int,
    @ColorInt endColor: Int,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null
) {
    var textWidthPx by remember { mutableStateOf(0f) }

    val brush = remember(textWidthPx, startColor, endColor) {
        Brush.linearGradient(
            colors = listOf(Color(startColor), Color(endColor)),
            start = Offset(0f, 0f),
            end = Offset(textWidthPx, 0f) // 텍스트 길이만큼
        )
    }

    Text(
        text = text,
        style = TextStyle(
            brush = brush,
            fontSize = fontSize,
            fontWeight = fontWeight
        ),
        modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
            textWidthPx = layoutCoordinates.size.width.toFloat()
        }
    )
}

