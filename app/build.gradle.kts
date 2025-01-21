import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.hilt.android)
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())
val apiKey = properties.getProperty("SERVICE_KEY") ?: ""


android {
    namespace = "com.example.certificate"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.certificate"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.certificate.CustomTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "SERVICE_KEY", apiKey)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"

            // 6 files found with path 'META-INF/LICENSE.md' from inputs ERROR
        }
    }


}

dependencies {

    // AndroidX Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.play.services.maps)
    implementation(libs.androidx.paging.common.android)

    // Retrofit & Gson
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)

    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.navcompose)
    kapt(libs.hilt.compiler)

    // OkHttp & Logging
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Testing Dependencies
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.mockk.android)

    // Compose Libraries
    implementation(libs.compose)
    implementation(libs.compose.m3)
    implementation(libs.core)
    implementation(libs.views)

    // Google Maps Libraries
    implementation(libs.maps.compose)
    implementation(libs.maps.compose.utils)
    implementation(libs.maps.compose.widgets)
    implementation(libs.accompanist.permissions)

    // Paging Libraries
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)

    // Debug & UI Tools
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Hilt Test Dependencies
    kaptAndroidTest(libs.hilt.compiler)
    testImplementation(libs.hilt.android.testing)
}
