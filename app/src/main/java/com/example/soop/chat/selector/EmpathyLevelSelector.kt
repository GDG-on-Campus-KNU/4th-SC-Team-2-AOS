package com.example.soop.chat.selector

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.soop.RoundedWhiteBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmpathyLevelSelector(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetVisible by remember { mutableStateOf(false) }

    val options = listOf(
        "Cool & Rational",
        "Factual",
        "Balanced",
        "Warm & Understanding",
        "Empathetic & Caring"
    )

    // 선택된 항목 보여주는 박스
    RoundedWhiteBox(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isSheetVisible = true }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedOption.ifEmpty { "Select an option" },
                color = if (selectedOption.isEmpty()) Color.Gray else Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }

    // 바텀 시트
    if (isSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isSheetVisible = false },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Empathy Level", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))

                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onOptionSelected(option)
                                isSheetVisible = false
                            }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(option, modifier = Modifier.weight(1f))
                        if (option == selectedOption) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.Green
                            )
                        }
                    }
                }
            }
        }
    }
}
