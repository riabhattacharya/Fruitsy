package com.newapp.fruitsy.appstats

import com.newapp.fruitsy.appstats.FruitAppStats.displayTimer
import com.newapp.fruitsy.appstats.FruitAppStats.startDisplayTimer
import com.newapp.fruitsy.modules.appModule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class FruitAppStatsTest : KoinTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init()
        startKoin {
            modules(appModule)
        }
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `test display timer started`() {
        startDisplayTimer()
        assertNotNull(displayTimer)
    }

    @Test
    fun `test display logger for valid display time`() = testDispatcher.runBlockingTest {
        FruitAppStats.logDisplayLoadTime(0)
    }

    @Test
    fun `test service logger for valid display time`() = testDispatcher.runBlockingTest {
        FruitAppStats.logServiceLoadTime(123)
    }

    @Test
    fun `test error logger for valid message`() = testDispatcher.runBlockingTest {
        FruitAppStats.logError("Sample Message")
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        stopKoin()
    }
}