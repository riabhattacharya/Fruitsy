package com.newapp.fruitsy.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.newapp.fruitsy.R
import com.newapp.fruitsy.appstats.FruitAppStats

class FruitMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!customExceptionHandlerAttached) {
            Thread.setDefaultUncaughtExceptionHandler { _, e ->
                handleUncaughtException(e)
            }
            customExceptionHandlerAttached = true
        }

        setContentView(R.layout.activity_fruit_main)
    }

    private fun handleUncaughtException(e: Throwable) {
        e.message?.let { FruitAppStats.logError(it) }
    }

    companion object {
        private var customExceptionHandlerAttached: Boolean = false
    }
}