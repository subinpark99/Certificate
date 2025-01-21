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


}
