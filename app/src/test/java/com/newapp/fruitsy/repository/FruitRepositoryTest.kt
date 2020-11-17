package com.newapp.fruitsy.repository

import com.newapp.fruitsy.R
import com.newapp.fruitsy.model.Fruit
import com.newapp.fruitsy.modules.appModule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class FruitRepositoryTest : KoinTest {

    private lateinit var fruitRepo: FruitRepository

    @RelaxedMockK
    private var mockFruit = Fruit(R.drawable.ic_photo, "type", 100F, 1000F)

    @RelaxedMockK
    private var listOfFruits = listOf(mockFruit)

    @BeforeAll
    fun setup() {
        MockKAnnotations.init(this)
        startKoin {
            modules(appModule)
        }
        fruitRepo = mockk()
    }

    @Test
    fun `test get list of fruits`() {
        coEvery {
            fruitRepo.getFruitsList()
        } returns listOfFruits
        runBlocking { fruitRepo.getFruitsList() }
        coVerify(atLeast = 1) { fruitRepo.getFruitsList() }
    }

    @Test
    fun `test get list of fruits returns empty list`() {
        coEvery {
            fruitRepo.getFruitsList()
        } returns listOf()
        runBlocking { fruitRepo.getFruitsList() }
        coVerify(atLeast = 1) { fruitRepo.getFruitsList() }
    }

    @AfterAll
    fun tearDown() {
        stopKoin()
    }
}