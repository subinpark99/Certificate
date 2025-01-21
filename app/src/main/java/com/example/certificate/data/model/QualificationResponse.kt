package com.example.certificate.data.model

import com.google.gson.annotations.SerializedName


data class QualificationResponse(
    @SerializedName("header") val header: Header,
    @SerializedName("body") val body: Body,
)

data class Header(
    @SerializedName("resultCode") val resultCode: String,
    @SerializedName("resultMsg") val resultMsg: String,
)

data class Body(
    @SerializedName("items") val items: List<Item>,
    @SerializedName("numOfRows") val numOfRows: Int,
    @SerializedName("pageNo") val pageNo: Int,
    @SerializedName("totalCount") val totalCount: Int,
)

data class Item(
    @SerializedName("rgnNm") val rgnNm: String? = null,         // 지역 명
    @SerializedName("ageGrupNm") val ageGrupNm: String? = null,     // 연령대 명
    @SerializedName("genderNm") val genderNm: String? = null,      // 성별 명
    @SerializedName("acquCnt") val acquCnt: Int,         // 취득 개수
)
