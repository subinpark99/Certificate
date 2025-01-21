package com.example.certificate

import com.example.certificate.data.repository.QualificationRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

//@Module
//@InstallIn(SingletonComponent::class)
//object TestAppModule {
//
//    @Provides
//    @Singleton
//    fun provideQualificationApi(): QualificationApi {
//        // 실제 API를 사용할 경우 Retrofit 인스턴스를 반환
//        return Retrofit.Builder()
//            .baseUrl("https://example.com") // 실제 API URL
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(QualificationApi::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideQualificationRepository(api: QualificationApi): QualificationRepository {
//        return QualificationRepository(api)
//    }
//}
