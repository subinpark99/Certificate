package com.example.certificate.presentation.ui.screen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.certificate.data.model.UiState
import com.example.certificate.presentation.ui.component.request.GetAcquisitionYear
import com.example.certificate.presentation.ui.component.request.GetCertificationName
import com.example.certificate.presentation.ui.theme.DarkBlue
import com.example.certificate.presentation.viewmodel.QualificationViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    qualificationViewModel: QualificationViewModel = hiltViewModel(),
) {
    val uiState by qualificationViewModel.uiState.collectAsState()

    var itemNameText by remember { mutableStateOf("") }
    var itemYearText by remember { mutableIntStateOf(0) }
    var onclick by remember { mutableStateOf(false) }

    // 클릭 상태가 true일 경우 ViewModel에서 데이터를 가져옴
    LaunchedEffect(onclick) {
        if (onclick) {
            qualificationViewModel.getQualificationData(itemYearText, itemNameText)
            onclick = false
        }
    }

    Column(modifier = Modifier.background(color = Color.White)) {

        GetCertificationName(itemName = itemNameText, onValueChanged = { itemNameText = it })
        GetAcquisitionYear(
            itemYear = itemYearText,
            onValueChanged = { itemYearText = it },
            onButtonClick = { onclick = it })  // 버튼 클릭 시 상태 업데이트


        // 데이터 로딩 상태 처리
        when (uiState) {
            is UiState.Idle -> IdleText(text = "종목명과 취득년도로 \n자격증 취득 현황 확인하기 ")    // 초기 상태

            is UiState.Loading -> LoadingIndicator()  // 로딩중

            is UiState.Success -> {  // 데이터 로드 성공

                val qualifications = (uiState as UiState.Success).data

                if(qualifications["region"].isNullOrEmpty()) {
                    ErrorText(text = "데이터 없음")
                }
                PagerScreenView(qualifications) // 데이터 표시
            }

            is UiState.Error -> {  // 에러 발생
                val errorMessage = (uiState as UiState.Error).message
                ErrorText(text = errorMessage)
            }
        }
    }
}

@Composable
fun IdleText(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        contentAlignment = Alignment.Center // 중앙 정렬
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center // 텍스트 중앙 정렬
        )
    }
}


@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(34.dp), // 크기 설정
            color = DarkBlue, // 진행 표시 색상
            trackColor = Color.Gray // 트랙 색상
        )
    }
}

@Composable
fun ErrorText(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: $text",
            color = Color.Red,
            textAlign = TextAlign.Center
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewBoard() {
    HomeScreen()
}