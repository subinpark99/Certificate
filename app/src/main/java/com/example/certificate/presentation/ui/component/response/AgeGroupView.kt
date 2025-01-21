package com.example.certificate.presentation.ui.component.response


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.certificate.presentation.ui.theme.Yellow
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf

@Composable
fun AgeGroupView(ageList: Map<String?, Int>) {

    val ageGroups = listOf("10대", "20대", "30대", "40대", "50대", "60대")

    val maxValue = ageList.values.maxOrNull() ?: 0 // 최대값 계산
    val maxCount = if (maxValue % 10 == 0) maxValue else ((maxValue / 10) + 1) * 10  // 최대값 기준 차트 스케일 조정

    // 차트 데이터 생성
    val chartEntries = ageGroups.mapIndexed { index, ageGroup ->
        val count = ageList[ageGroup] ?: 0 // 해당 연령대 데이터 없으면 0으로 처리
        entryOf(x = index, y = count)   // x축은 index, y축은 연령대 값
    }
    val chartModelProducer = ChartEntryModelProducer(chartEntries)  // ChartModelProducer로 차트 모델 생성

    Chart(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp, bottom = 15.dp, start = 10.dp, end = 15.dp),
        chart = columnChart(
            columns = listOf(
                lineComponent(
                    color = Yellow,
                    thickness = 15.dp,
                    shape = Shapes.cutCornerShape(topRightPercent = 20, topLeftPercent = 20)
                )
            ),
            dataLabel = TextComponent.Builder().build(),  // 데이터 레이블 생성
            axisValuesOverrider = AxisValuesOverrider.fixed(
                minY = 0f,
                maxY = maxCount.toFloat()
            )
        ),
        chartModelProducer = chartModelProducer,   // 차트 데이터 전달
        startAxis = rememberStartAxis(
            itemPlacer = AxisItemPlacer.Vertical.default(maxItemCount = 6)  // y축 항목 최대 표시 수
        ),
        bottomAxis = rememberBottomAxis(
            valueFormatter = { value, _ ->
                ageGroups.getOrNull(value.toInt()) ?: "" // x축 레이블 설정
            }
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBoardAge() {
    AgeGroupView(mapOf())
}