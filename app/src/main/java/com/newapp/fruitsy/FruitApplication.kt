package com.newapp.fruitsy

import android.app.Application
import com.newapp.fruitsy.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FruitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FruitApplication)
            modules(appModule)
        }
    }
}