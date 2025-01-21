package com.example.certificate.presentation.ui.component.request

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.certificate.presentation.ui.theme.Gray
import java.time.Year

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GetAcquisitionYear(
    itemYear: Int,
    onValueChanged: (Int) -> Unit,
    onButtonClick: (Boolean) -> Unit,
) {

    var isDropdownExpanded by remember { mutableStateOf(false) }

    val currentYear = Year.now().value
    val options = (2000..currentYear).toList().asReversed() // 연도 생성

    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "취득년도", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Box(
            modifier = Modifier
                .clickable {
                    isDropdownExpanded = !isDropdownExpanded  // 클릭 시 드롭다운 상태 변경
                },
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = itemYear.toString(), fontSize = 20.sp)

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "dropdown",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 5.dp),
                    tint = Color.Black
                )
            }


            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false },
                modifier = Modifier
                    .background(Gray)
                    .height(400.dp)
            ) {
                options.forEach { option ->  // 연도별 목록 생성
                    DropdownMenuItem(
                        text = { Text(text = option.toString(), fontSize = 17.sp) },
                        onClick = {
                            onValueChanged(option)   // 선택된 연도 전달
                            isDropdownExpanded = false
                        },
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                onButtonClick(true)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
        ) {
            Text(
                text = "완료",
                fontSize = 15.sp,
                modifier = Modifier.padding(2.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewBoard() {
    GetAcquisitionYear(2023, onValueChanged = {}, onButtonClick = {})
}