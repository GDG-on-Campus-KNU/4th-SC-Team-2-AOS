package com.example.soop.home.item

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.R
import com.example.soop.RoundedWhiteBox
import com.example.soop.Text12sp
import com.example.soop.home.response.ExpertResponse
import com.example.soop.itemlist.ExpertImage
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun RecommendedExpertItem(recommendedExpertItemData: ExpertResponse) {
    val imageIndex = recommendedExpertItemData.image
    val name = recommendedExpertItemData.nickname
    val bio = recommendedExpertItemData.bio

    RoundedWhiteBox(modifier = Modifier.size(width = 171.dp, height = 161.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row() {
                ExpertImage(index = imageIndex, size = 44)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = name,
                    style = TextStyle(fontSize = 16.sp, color = Color.Black),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Text12sp(text = bio)
            Box(modifier = Modifier.fillMaxSize()){
                Image(painter = painterResource(id = R.drawable.right_up_arrow), contentDescription = "move to expert chat",
                    modifier = Modifier.size(44.dp).align(Alignment.BottomEnd)
                )
            }
        }
    }
}