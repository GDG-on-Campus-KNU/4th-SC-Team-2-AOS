package com.example.soop.report.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.R
import com.example.soop.RoundedWhiteBox
import com.example.soop.report.item.NegativeItem
import com.example.soop.report.item.PositiveItem
import com.example.soop.report.response.Trigger

@Composable
fun NegativeTrigger(negativeTrigger: List<Trigger>) {
    RoundedWhiteBox {
        Column(modifier = Modifier.fillMaxWidth()){
            Text("Negative Trigger", fontSize = 18.sp)
            Spacer(Modifier.padding(8.dp))
            negativeTrigger.forEachIndexed { index, trigger ->
                Spacer(modifier = Modifier.padding(4.dp))
                NegativeItem(image = when(index){
                    0->R.drawable.num1
                    1->R.drawable.num2
                    2->R.drawable.num3
                    else->R.drawable.num1
                }, text1 = trigger.trigger, num = trigger.count.toString(), modifier = Modifier.fillMaxWidth())            }
        }
    }
}