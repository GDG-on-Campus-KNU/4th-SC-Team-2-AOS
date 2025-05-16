package com.example.soop.chat.selector

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text // Material 3 Text 사용
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup // ✅ Popup 임포트
import androidx.compose.ui.window.PopupProperties // ✅ PopupProperties 임포트
import com.example.soop.chat.CreateCustomActivity // CreateCustomActivity 임포트 확인

@Composable
fun ChatSelector(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    options: List<String>,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier
) {
    if (expanded) {
        Popup(
            onDismissRequest = onDismissRequest,
            properties = PopupProperties(focusable = true),
            alignment = Alignment.TopEnd
        ) {
            Column(
                modifier = modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(vertical = 8.dp)
            ) {
                options.forEachIndexed { index, optionText ->
                    Text(
                        text = optionText,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .clickable {
                                onOptionSelected(index)
                                onDismissRequest()
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}