package com.example.certificate

import com.example.certificate.data.model.UiState
import com.example.certificate.data.repository.QualificationRepository
import com.example.certificate.presentation.viewmodel.QualificationViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class QualificationViewModelTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: QualificationRepository // Repository를 주입

    //lateinit var viewModel: QualificationViewModel

    @Before
    fun setup() {
        hiltRule.inject() // Hilt 주입

        // viewModel = QualificationViewModel(repository)
    }

    @Test
    fun fetchAllQualificationData_returnsCorrectData() = runBlocking {
        val year = 2023
        val itemName = "정보처리기사"

        val result = repository.fetchAllQualificationData(
            acquYy = year,
            jmNm = itemName
        )

        assertTrue(result.isNotEmpty()) // 결과 데이터가 비어있지 않음을 확인
    }
    
    @Test
    fun fetchQualifications_updatesUiStateSuccessfully() = runTest {

        val year = 2023
        val itemName = "정보처리기사"

        val viewmodel = QualificationViewModel(repository)

        viewmodel.getQualificationData(acquYy = year, jmNm = itemName)
        assertTrue(viewmodel.uiState.value is UiState.Success)


    }
}
