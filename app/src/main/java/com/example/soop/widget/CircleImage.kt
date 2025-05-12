package com.example.soop.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.math.round

@Composable
fun CircleImage(
    image: Int,
    size: Int = 44,
    contentDescription : String
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
    )
}