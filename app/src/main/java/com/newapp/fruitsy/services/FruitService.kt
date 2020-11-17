package com.newapp.fruitsy.services

import com.newapp.fruitsy.model.FruitList
import com.newapp.fruitsy.util.GET_FRUITS_LIST
import retrofit2.http.GET

interface FruitService {
    @GET(GET_FRUITS_LIST)
    suspend fun getListOfFruits(): FruitList
}