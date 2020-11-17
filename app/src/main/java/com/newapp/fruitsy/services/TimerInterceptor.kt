package com.newapp.fruitsy.services

import com.newapp.fruitsy.appstats.FruitAppStats
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*


class TimerInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestTime = Calendar.getInstance().timeInMillis
        val response = chain.proceed(chain.request())
        if(response.code == 200) {
            FruitAppStats.logServiceLoadTime(timeDifference = Calendar.getInstance().timeInMillis - requestTime)
        } else {
            FruitAppStats.logError("${response.code}  ${response.message}")
        }
        return response
    }

}
