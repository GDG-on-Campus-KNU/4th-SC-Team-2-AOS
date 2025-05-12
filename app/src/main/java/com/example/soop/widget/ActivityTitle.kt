package com.example.soop.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.soop.R

@Composable
fun ActivityTitle(
    leftIcon: Int? = null,
    onLeftClick: (() -> Unit)? = null,
    title: String,
    rightIcon: Int? = null,
    onRightClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        leftIcon?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = "left icon",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterStart)
                    .clickable(enabled = onLeftClick != null){
                        onLeftClick?.invoke()
                    }
            )
        }

        Text20sp(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )

        rightIcon?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = "back to chat screen",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd)
                    .clickable(enabled = onRightClick != null){
                    onRightClick?.invoke()
                }
            )
        }
    }
}