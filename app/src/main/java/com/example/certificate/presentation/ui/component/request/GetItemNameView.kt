package com.example.certificate.presentation.ui.component.request

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.certificate.presentation.ui.theme.Gray

@Composable
fun GetCertificationName(
    itemName: String,
    onValueChanged: (String) -> Unit,
) {

    Row(
        modifier = Modifier.padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "종목명", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        BasicTextField(
            value = itemName,
            singleLine = true,
            onValueChange = onValueChanged,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Gray, shape = RoundedCornerShape(size = 5.dp))
                        .padding(all = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))

                    innerTextField()
                }
            }
        )

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewBoard3() {
    GetCertificationName("정보처리기사", onValueChanged = {})
}