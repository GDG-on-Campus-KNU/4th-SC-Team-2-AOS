package com.example.soop.itemlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.soop.R
import com.example.soop.widget.CircleImage

@Composable
fun ExpertImage(index: Int, size: Int) {
    val imageRes = when (index) {
        0 -> R.drawable.expert1
        1 -> R.drawable.expert2
        2 -> R.drawable.expert3
        3 -> R.drawable.expert4
        4 -> R.drawable.expert5
        5 -> R.drawable.expert6
        6 -> R.drawable.expert7
        7 -> R.drawable.expert8
        8 -> R.drawable.expert9
        else -> R.drawable.expert1 // 기본값
    }

    CircleImage(
        image = imageRes,
        contentDescription = "expert image",
    )
}