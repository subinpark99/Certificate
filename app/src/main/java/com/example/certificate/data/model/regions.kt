package com.example.certificate.data.model

import com.google.android.gms.maps.model.LatLng

data class RegionMarker(
    val name: String,
    val position: LatLng,
)

val regions = listOf(
    RegionMarker("서울", LatLng(37.5665, 126.9780)),
    RegionMarker("부산", LatLng(35.1796, 129.0756)),
    RegionMarker("대구", LatLng(35.8722, 128.6025)),
    RegionMarker("인천", LatLng(37.4563, 126.7052)),
    RegionMarker("광주", LatLng(35.1595, 126.8526)),
    RegionMarker("대전", LatLng(36.3504, 127.3845)),
    RegionMarker("울산", LatLng(35.5392, 129.3114)),
    RegionMarker("세종", LatLng(36.4800, 127.2890)),
    RegionMarker("경기", LatLng(37.2750, 127.0090)),
    RegionMarker("강원", LatLng(37.8228, 128.1555)),
    RegionMarker("충북", LatLng(36.6356, 127.4912)),
    RegionMarker("충남", LatLng(36.5184, 126.8000)),
    RegionMarker("전북", LatLng(35.8205, 127.1088)),
    RegionMarker("전남", LatLng(34.8679, 126.9910)),
    RegionMarker("경북", LatLng(36.4919, 128.8889)),
    RegionMarker("경남", LatLng(35.2383, 128.6920)),
    RegionMarker("제주", LatLng(33.4996, 126.5312))
)