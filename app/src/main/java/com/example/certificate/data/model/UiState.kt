package com.example.certificate.data.model

sealed class UiState {
    object Idle : UiState() // 초기 상태
    object Loading : UiState()
    data class Success(val data: Map<String, Map<String?, Int>>) : UiState()
    data class Error(val message: String) : UiState()
}