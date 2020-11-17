package com.newapp.fruitsy.services

import com.newapp.fruitsy.util.SEND_APP_STATS
import retrofit2.http.GET
import retrofit2.http.Query

interface AppStatsService {
    @GET(SEND_APP_STATS) //Naming service as SEND (instead of GET) as this will send data to server
    suspend fun sendAppStats(
        @Query("event", encoded = true) event: String,
        @Query("data", encoded = true) data: String,
    )
}