package com.example.certificate.data.remote


import com.example.certificate.data.model.QualificationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QualificationApi {
    @GET("getQualAcquPtcond")
    suspend fun getQualificationData(
        @Query("serviceKey") apiKey: String,
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
        @Query("dataFormat") dataFormat: String,
        @Query("acquYy") acquYy: Int, // 취득년도
        @Query("qualgbCd") qualgbCd: String,  // 자격구분코드
        @Query("jmNm") jmNm: String, // 자격증 이름
        @Query("rgnCd") rgnCd: String? = null, // 지역 코드
        @Query("ageGrupCd") ageGrupCd: Int? = null, // 연령대 코드
        @Query("genderCd") genderCd: String? = null // 성별 코드
    ): Response<QualificationResponse>
}