package com.newapp.fruitsy.appstats

import androidx.annotation.VisibleForTesting
import com.newapp.fruitsy.services.appStatsService
import com.newapp.fruitsy.util.EVENT_DISPLAY
import com.newapp.fruitsy.util.EVENT_ERROR
import com.newapp.fruitsy.util.EVENT_LOAD
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

object FruitAppStats {
    @VisibleForTesting
    var displayTimer: Long = 0
    fun startDisplayTimer() {
        displayTimer = Calendar.getInstance().timeInMillis
    }

    fun stopAndLogDisplayTimer() {
        if (displayTimer > 0) {
            val displayTime = Calendar.getInstance().timeInMillis - displayTimer
            logDisplayLoadTime(displayTime)
            displayTimer = 0
        } else {
            //Start Time of the Service was not captured. Need further implementation to handle edge cases
        }

    }

    fun logServiceLoadTime(timeDifference: Long) {
        runBlocking<Unit> {
            launch { appStatsService.sendAppStats(EVENT_LOAD, timeDifference.toString()) }
        }
    }

    @VisibleForTesting
    fun logDisplayLoadTime(timeDifference: Long) {
        runBlocking<Unit> {
            launch { appStatsService.sendAppStats(EVENT_DISPLAY, timeDifference.toString()) }
        }
    }

    fun logError(errorMsg: String) {
        runBlocking<Unit> {
            launch { appStatsService.sendAppStats(EVENT_ERROR, errorMsg) }
        }
    }
}