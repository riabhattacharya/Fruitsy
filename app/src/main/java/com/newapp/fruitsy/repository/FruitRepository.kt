package com.newapp.fruitsy.repository

import com.newapp.fruitsy.model.Fruit
import com.newapp.fruitsy.services.fruitService

interface FruitRepository {
    suspend fun getFruitsList(): List<Fruit>
    fun clearListOfFruits()
}

class FruitRepositoryImpl : FruitRepository {
    private var listOfFruits: List<Fruit> = emptyList()
    override suspend fun getFruitsList(): List<Fruit> {
        if(listOfFruits.isEmpty()) {
            val fruitList = fruitService.getListOfFruits()
            listOfFruits = fruitList.fruit
        }
        return listOfFruits
    }

    override fun clearListOfFruits() {
        listOfFruits = emptyList()
    }
}