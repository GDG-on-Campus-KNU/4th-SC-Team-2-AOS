package com.example.soop.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SendButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
){
    Button(
        onClick = { onClick?.invoke() },
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1CE0A9)),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = text,
            style = TextStyle(color = Color.White, fontSize = 16.sp)
        )
    }
}