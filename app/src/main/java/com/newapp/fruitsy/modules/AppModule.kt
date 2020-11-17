package com.newapp.fruitsy.modules

import com.newapp.fruitsy.repository.FruitRepository
import com.newapp.fruitsy.repository.FruitRepositoryImpl
import com.newapp.fruitsy.viewmodel.FruitViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<FruitRepository> { FruitRepositoryImpl() }
    viewModel { FruitViewModel(get()) }
}