package com.example.certificate

import com.example.certificate.data.repository.QualificationRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class QualificationRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: QualificationRepository

    @Before
    fun setup() {
        hiltRule.inject() // Hilt 주입
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
}
