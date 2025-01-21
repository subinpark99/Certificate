package com.example.certificate.presentation.ui.component.response

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.certificate.presentation.ui.theme.Green
import com.example.certificate.presentation.ui.theme.Orange


@Composable
fun GenderGroupView(genderList:Map<String?, Int>) {

    val total = genderList.values.sum()

    Column(modifier = Modifier.padding(20.dp)) {
        GenderRatioGraph(data = genderList, modifier = Modifier.fillMaxWidth())
        GenderDataGrid(data = genderList, total = total)
    }
}

@Composable
fun GenderRatioGraph(
    modifier: Modifier = Modifier,
    data: Map<String?, Int>
) {
    val total = data.values.sum()
    val graphHeight = 260  // 그래프 높이

    // 성별 비율에 맞는 각도 계산
    val angles = data.map { (_, count) -> count.toFloat() / total * 360f }

    Canvas(
        modifier = modifier
            .height(graphHeight.dp)
    ) {
        val strokeWidth = graphHeight.dp.toPx() / 4  // 원형 그래프 두께 설정
        val radius = (graphHeight.dp.toPx() - strokeWidth) / 2  // 반지름 계산

        drawGraph(angles, radius, strokeWidth)
    }
}

private fun DrawScope.drawGraph(
    angles: List<Float>,
    radius: Float,
    strokeWidth: Float
) {

    val colors = listOf(Green, Orange, Color.Gray)  // 성별에 따른 색상 설정
    var startAngle = -90f  // 시작 각도 설정

    angles.forEachIndexed { index, angle ->
        drawArc(
            color = colors.getOrNull(index) ?: Color.Gray,
            startAngle = startAngle,
            sweepAngle = angle,  // 그려야하는 각도
            useCenter = false,
            style = Stroke(width = strokeWidth),  // 선 두께 설정
            topLeft = Offset(center.x - radius, center.y - radius),   // 원의 좌측 상단 좌표
            size = Size(radius * 2, radius * 2)
        )
        startAngle += angle  // 다음 시작 각도 설정
    }

}

@Composable
fun GenderDataGrid(
    data: Map<String?, Int>,
    total: Int
) {
    val genderColors = mapOf("남성" to Green, "여성" to Orange)  // 성별별 색상

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2열 그리드
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentPadding = PaddingValues(5.dp)
    ) {

        data.forEach { (gender, count) ->
            item {
                GridItem(
                    gender = gender ?: "Unknown",  // 성별이 없으면 "Unknown"으로 표시
                    count = "$count",  // 해당 성별 데이터
                    color = genderColors[gender] ?: Color.Gray  // 성별 색상
                )
            }
        }
        item {
            GridItem(
                gender = "총합",  // 총합 아이템
                count = "$total", // 총합 데이터
                color = Color.Black  // 색상 설정
            )
        }
    }
}

@Composable
fun GridItem(gender: String, count: String, color: Color) {
    Row(
        modifier = Modifier
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = gender,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = color,
        )

        Text(
            text = "$count 명",
            fontSize = 15.sp,
            color = Color.Black
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBoardGender() {
    GenderGroupView(mapOf())
}