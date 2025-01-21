package com.example.certificate.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.certificate.presentation.ui.component.response.AgeGroupView
import com.example.certificate.presentation.ui.component.response.GenderGroupView
import com.example.certificate.presentation.ui.component.response.RegionNameView


@Composable
fun PagerScreenView(
    groupedData: Map<String, Map<String?, Int>>
) {

    val regionData = groupedData["region"] ?: emptyMap()
    val ageGroupData = groupedData["ageGroup"] ?: emptyMap()
    val genderData = groupedData["gender"] ?: emptyMap()

    val pagerState = rememberPagerState(pageCount = { 3 })  // pager 상태 관리

    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(0.8f),
            beyondViewportPageCount = 2  // 뷰포트 너머의 페이지 개수 설정

        ) { page ->
            when (page) {
                0 -> RegionNameView(regionData)
                1 -> AgeGroupView(ageGroupData)
                2 -> GenderGroupView(genderData)
            }
        }

        // Pager의 하단 점을 표시하는 Indicator
        Row(
            Modifier
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(pagerState.pageCount) { iteration ->  // 페이지 수만큼 점을 생성
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBoardPager() {
    PagerScreenView(mapOf())
}

