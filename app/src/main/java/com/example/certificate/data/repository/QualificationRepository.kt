package com.example.certificate.data.repository

import android.util.Log
import com.example.certificate.BuildConfig
import com.example.certificate.data.model.Item
import com.example.certificate.data.remote.QualificationApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QualificationRepository @Inject constructor(
    private val api: QualificationApi
) {
    // 모든 페이지 데이터 가져오기
    suspend fun getAllQualificationData(
        acquYy: Int,
        jmNm: String,
        rgnCd: String? = null,
        ageGrupCd: Int? = null,
        genderCd: String? = null
    ): List<Item> {
        val allItems = mutableListOf<Item>() // 모든 결과 저장
        var pageNo = 1
        val numOfRows = 50  // 한 페이지에 가져올 데이터 수

        while (true) {
            val response = api.getQualificationData(
                BuildConfig.SERVICE_KEY, numOfRows, pageNo, "json", acquYy, "T", jmNm, rgnCd, ageGrupCd, genderCd
            )

            if (response.isSuccessful) {
                val body = response.body()?.body
                body?.items?.let { allItems.addAll(it) }  // 결과 리스트에 추가
                if ((body?.items?.size ?: 0) < numOfRows) break  // 마지막 페이지 확인
                pageNo++ // 다음 페이지로 이동

                Log.e("RESPONSE",body.toString())

            } else {
                throw Exception("API Error: ${response.message()}")
            }
        }
        return allItems // 모든 결과 반환
    }
}



