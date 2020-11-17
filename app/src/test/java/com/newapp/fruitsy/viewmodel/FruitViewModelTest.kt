package com.newapp.fruitsy.viewmodel

import androidx.lifecycle.MutableLiveData
import com.newapp.fruitsy.R
import com.newapp.fruitsy.model.Fruit
import com.newapp.fruitsy.modules.appModule
import com.newapp.fruitsy.repository.FruitRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class FruitViewModelTest : KoinTest {

    private lateinit var fruitViewModel: FruitViewModel
    private lateinit var fruitRepo: FruitRepository

    @RelaxedMockK
    private var mockFruit = Fruit(R.drawable.ic_photo, "type", 100F, 1000F)

    @RelaxedMockK
    private var fruitsLiveData = MutableLiveData(listOf(mockFruit))

    private val testDispatcher = TestCoroutineDispatcher()

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
        startKoin {
            modules(appModule)
        }
        fruitRepo = mockk()
        fruitViewModel = mockk()
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `test get list of fruits with valid data`() = testDispatcher.runBlockingTest {
        every { fruitViewModel.getListOfFruits() } returns fruitsLiveData
        fruitViewModel.getListOfFruits()
        coVerify(exactly = 1) { fruitViewModel.getListOfFruits() }
    }

    @AfterAll
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        stopKoin()
    }
}