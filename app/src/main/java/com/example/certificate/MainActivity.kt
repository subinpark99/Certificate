package com.example.certificate

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.certificate.presentation.ui.screen.HomeScreen
import com.example.certificate.presentation.ui.theme.CertificateTheme
import dagger.hilt.android.AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CertificateTheme {
                HomeScreen()
            }
        }
    }
}
