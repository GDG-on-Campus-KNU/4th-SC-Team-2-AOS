package com.example.soop.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.soop.R
import com.example.soop.Text32sp

@Composable
fun FragmentTitle(
    title: String,
    onClick: (() -> Unit)? = null,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)) {
        Text32sp(title)
        Image(painter = painterResource(
            id = R.drawable.add),
            contentDescription = "add button",
            Modifier
                .size(40.dp)
                .clickable(enabled = onClick != null) {
                    onClick?.invoke()
                }
        )
    }
}