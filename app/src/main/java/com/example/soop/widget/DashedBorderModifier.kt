package com.example.soop.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalDensity

@Composable
fun DashedBorderModifier(
    color: Color = Color.Black,
    strokeWidth: Dp = 1.dp,
    dashLength: Dp = 8.dp,
    gapLength: Dp = 4.dp
): Modifier {
    val density = LocalDensity.current
    val strokePx = with(density) { strokeWidth.toPx() }
    val dashPx = with(density) { dashLength.toPx() }
    val gapPx = with(density) { gapLength.toPx() }

    return Modifier.drawBehind {
        val radius = size.minDimension / 2 - strokePx / 2
        drawIntoCanvas { canvas ->
            val paint = Paint().apply {
                this.color = color
                this.strokeWidth = strokePx
                this.pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashPx, gapPx), 0f)
                this.style = PaintingStyle.Stroke
            }
            canvas.drawCircle(center, radius, paint)
        }
    }
}
