package com.example.certificate.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.certificate.data.model.Item
import com.example.certificate.data.model.UiState
import com.example.certificate.data.repository.QualificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QualificationViewModel @Inject constructor(
    private val repository: QualificationRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    fun getQualificationData(
        acquYy: Int,
        jmNm: String,
        rgnCd: String? = null,
        ageGrupCd: Int? = null,
        genderCd: String? = null,
    ) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading // 로딩 시작

            try {
                val data =
                    repository.getAllQualificationData(acquYy, jmNm, rgnCd, ageGrupCd, genderCd)


                _uiState.value =
                    UiState.Success(calculateGroupedData(data)) // 데이터 로드 성공

            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error") // 에러 처리
            }
        }
    }
}


fun calculateGroupedData(itemList: List<Item>): Map<String, Map<String?, Int>> {
    return mapOf(
        "region" to itemList.groupBy { it.rgnNm }
            .mapValues { (_, items) -> items.sumOf { it.acquCnt } },
        "ageGroup" to itemList.groupBy { it.ageGrupNm }
            .mapValues { (_, items) -> items.sumOf { it.acquCnt } },
        "gender" to itemList.groupBy { it.genderNm }
            .mapValues { (_, items) -> items.sumOf { it.acquCnt } }
    )
}