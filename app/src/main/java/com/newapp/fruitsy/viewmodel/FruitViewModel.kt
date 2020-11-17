package com.newapp.fruitsy.viewmodel

import androidx.lifecycle.*
import com.newapp.fruitsy.model.Fruit
import com.newapp.fruitsy.repository.FruitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FruitViewModel(private val fruitRepo: FruitRepository) : ViewModel() {
    val fruits = MutableLiveData<List<Fruit>>()

//    val fruits = liveData(Dispatchers.IO) {
//        val listOfFruits = fruitRepo.getFruitsList()
//        updateFruitImages()
//        emit(listOfFruits)
//    }

    private fun updateFruitImages() {
        //TODO
    }

    fun getListOfFruits(): LiveData<List<Fruit>> {
        viewModelScope.launch {
            fruits.postValue(fruitRepo.getFruitsList())
        }
        return fruits
    }

    fun refreshListOfFruits() {
        fruitRepo.clearListOfFruits()
        getListOfFruits()
    }

}