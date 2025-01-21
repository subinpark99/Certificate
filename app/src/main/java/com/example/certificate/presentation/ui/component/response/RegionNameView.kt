package com.example.certificate.presentation.ui.component.response


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.certificate.R
import com.example.certificate.data.model.regions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState


@SuppressLint("UnrememberedMutableState")
@Composable
fun RegionNameView(regionList: Map<String?, Int>) {

    val korea = LatLng(35.9078, 127.7669)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(korea, 6.85f) // 카메라 초기 위치와 줌 레벨 설정
    }

    val context = LocalContext.current

    GoogleMap(
        cameraPositionState = cameraPositionState, // 카메라 상태 설정
        uiSettings = MapUiSettings(
            tiltGesturesEnabled = false,  // 기울기 제스처 비활성화
            zoomControlsEnabled = false, // 줌 컨트롤 비활성화
            myLocationButtonEnabled = false  // 내 위치 버튼 비활성화
        ),
        modifier = Modifier.fillMaxSize()
    ) {

        // 마커 아이콘을 비트맵으로 변환하여 GoogleMap 마커로 사용
        val mapIconBitMap =
            BitmapFactory.decodeResource(context.resources, R.drawable.icon_location)
        val scaledBitmap = Bitmap.createScaledBitmap(mapIconBitMap, 90, 90, false)
        val markerIcon = BitmapDescriptorFactory.fromBitmap(scaledBitmap)

        regions.forEach { region ->
            val markerState = rememberMarkerState(position = region.position)

            // 클릭 시 보여줄 정보창
            MarkerInfoWindow(
                state = markerState,
                title = region.name,
                icon = markerIcon,
                onClick = { markerState.showInfoWindow(); true } // 마커 클릭 시 정보창 표시

            ) { marker ->
                RegionInfoWindow(marker.title.toString(), regionList[region.name].toString())
            }
        }
    }
}


@Composable
fun RegionInfoWindow(title: String, count: String) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, color = Black)
        Text(text = count, color = Black)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBoardRegin() {
    RegionNameView(mapOf())
}