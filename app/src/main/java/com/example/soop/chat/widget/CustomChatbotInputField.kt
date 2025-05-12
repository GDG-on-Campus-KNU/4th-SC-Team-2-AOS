package com.example.soop.chat.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.RoundedWhiteBox
import com.example.soop.R
import com.example.soop.chat.selector.EmpathyLevelSelector
import com.example.soop.chat.selector.ToneSelector
import com.example.soop.chat.viewmodel.CustomChatbotViewModel

class CustomChatbotInputField {
    @Composable
    fun CustomChatbotNameInputField(viewModel: CustomChatbotViewModel){
        val uiState by viewModel.uiState.collectAsState()
        val name = uiState.name

        Column {
            Text(text = "Name", fontSize = 16.sp, style = TextStyle(color = Color.Black))
            Spacer(modifier = Modifier.padding(5.dp))
            RoundedWhiteBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                BasicTextField(
                    value = name,
                    onValueChange = { viewModel.onNameChange(it) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    ),
                    decorationBox = { innerTextField ->
                        if (name.isEmpty()) {
                            Text(
                                "Write down the name of this bot.",
                                color = colorResource(id = R.color.middle_gray_text)
                            )
                        }
                        innerTextField()
                    }
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }

    @Composable
    fun CustomChatbotDescriptionInputField(viewModel: CustomChatbotViewModel){
        val uiState by viewModel.uiState.collectAsState()
        val description = uiState.description

        Column() {
            Text(text = "Description", fontSize = 16.sp, style = TextStyle(color = Color.Black))
            Spacer(modifier = Modifier.padding(5.dp))
            RoundedWhiteBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                BasicTextField(
                    value = description,
                    onValueChange = { viewModel.onDescriptionChange(it) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    ),
                    decorationBox = { innerTextField ->
                        if (description.isEmpty()) {
                            Text(
                                "Write a brief description of this bot.\n" +
                                        "(e.g., I want you to give me a lot of sweet, rational advice.)",
                                color = colorResource(id = R.color.middle_gray_text)
                            )
                        }
                        innerTextField()
                    }
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }

    @Composable
    fun CustomChatbotEmpathyLevelInputField(viewModel: CustomChatbotViewModel){
        val uiState by viewModel.uiState.collectAsState()
        val empathyLevel = uiState.empathyLevel

        Column() {
            Text(text = "Empathy Level", fontSize = 16.sp, style = TextStyle(color = Color.Black))
            Spacer(modifier = Modifier.padding(5.dp))
            EmpathyLevelSelector(
                selectedOption = empathyLevel,
                onOptionSelected = { viewModel.onEmpathyLevelChange(it) }
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }

    @Composable
    fun CustomChatbotToneInputField(viewModel: CustomChatbotViewModel){
        val uiState by viewModel.uiState.collectAsState()
        val tone = uiState.tone

        Column() {
            Text(text = "Tone", fontSize = 16.sp, style = TextStyle(color = Color.Black))
            Spacer(modifier = Modifier.padding(5.dp))
            ToneSelector(
                selectedOption = tone,
                onOptionSelected = { viewModel.onToneChange(it) }
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}
